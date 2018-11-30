package com.cleverlove.service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public interface ChartService {

    default JFreeChart getJFreeChart(String title, DefaultPieDataset pieDataset) {
        JFreeChart chart = ChartFactory.createPieChart3D(title, pieDataset, true, false, false);
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 20));
        //设置百分比
        PiePlot pieplot = (PiePlot) chart.getPlot();
        DecimalFormat df = new DecimalFormat("0.00%");//获得一个DecimalFormat对象，主要是设置小数问题
        NumberFormat nf = NumberFormat.getNumberInstance();//获得一个NumberFormat对象
        StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{1}占{2}", nf, df);//获得StandardPieSectionLabelGenerator对象
        pieplot.setLabelGenerator(sp1);//设置饼图显示百分比

        //没有数据的时候显示的内容
        pieplot.setNoDataMessage("无数据显示");
        pieplot.setCircular(false);
        pieplot.setLabelGap(0.02D);

        pieplot.setIgnoreNullValues(true);//设置不显示空值
        pieplot.setIgnoreZeroValues(true);//设置不显示负值
        pieplot.setLabelFont(new Font("宋体", Font.BOLD, 20));//解决乱码
        return chart;
    }
}
