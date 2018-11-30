package com.cleverlove.view.panel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class PieChartPanel extends ChartPanel {


    public PieChartPanel(JFreeChart chart, boolean useBuffer) {
        super(chart, useBuffer);
//        super(chart, 500,400,300,300, 500, 400, useBuffer, true, true, true, true, true);
//        super.setBackground(new Color(255,255,255));
    }
}
