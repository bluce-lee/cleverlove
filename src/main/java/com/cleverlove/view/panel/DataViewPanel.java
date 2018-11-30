package com.cleverlove.view.panel;

import com.cleverlove.entity.Msg;
import com.cleverlove.service.ChartService;
import com.cleverlove.service.FileService;
import com.cleverlove.service.FileServiceImpl;
import com.cleverlove.service.TestCaseResultAnalizeService;
import com.cleverlove.utils.RegexUtil;
import com.cleverlove.viewholder.panel.DataViewPanelViewHolder;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;

public class DataViewPanel extends JPanel implements Observer, ChartService, TestCaseResultAnalizeService{

    private DataViewPanelViewHolder dataViewPanelViewHolder;
    public FileService fileService;
    public ChartService chartService;
    public TestCaseResultAnalizeService testCaseResultAnalizeService;

    public DataViewPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        this.setBackground(new Color(255, 255, 255));
        dataViewPanelViewHolder = new DataViewPanelViewHolder(this);
        fileService = new FileServiceImpl();
        chartService = this;
        testCaseResultAnalizeService = this;
    }

    @Override
    public void update(Observable o, Object arg) {
        Msg msg = (Msg) arg;
        if (msg.getType() == 2) {
            String title = msg.getContent().get(Msg.MSG_SINGATURE);
            title = title.substring(0,title.lastIndexOf('.'));
            dataViewPanelViewHolder.jLabel.setText(title + "分析结果");

            JList list = dataViewPanelViewHolder.codeFileSelectPanel.codeFileSelectPanelViewHolder.jList;
            String selecteCodeFilename = (String) list.getSelectedValue();
            if (selecteCodeFilename != null) {
                Map<String, File> map = dataViewPanelViewHolder.codeFileSelectPanel.fileMap;
                String codeFileContent = fileService.getFileContent(map.get(selecteCodeFilename));
                int totalLog0 = RegexUtil.log0Num(codeFileContent);
                int coverLog0 = 0;
                selecteCodeFilename = selecteCodeFilename.substring(0, selecteCodeFilename.lastIndexOf('.'));
                if (msg.getFileNameList().size() == 1) {
                    coverLog0 = testCaseResultAnalizeService.getLogSetSize(selecteCodeFilename + msg.getFileNameList().get(0));
                } else {
                    Set<String> totalLogSet = new HashSet<>();
                    for (String fileName : msg.getFileNameList()) {
                        Set<String> logSet = testCaseResultAnalizeService.getLogSet(selecteCodeFilename + fileName);
                        for (String log : logSet) {
                            if (!totalLogSet.contains(log)) {
                                totalLogSet.add(log);
                            }
                        }
                    }
                    coverLog0 = totalLogSet.size();
                }
                DefaultPieDataset dataset = new DefaultPieDataset();
                dataset.setValue("已覆盖", coverLog0);
                dataset.setValue("未覆盖", totalLog0 - coverLog0);
                chart(selecteCodeFilename,"分析结果图", dataset);
            }
        }
    }

    private void chart(String tabTitle, String chartTitle,DefaultPieDataset dataset) {
        dataViewPanelViewHolder.chartJTabbedPane.removeAll();
        dataViewPanelViewHolder.chartJTabbedPane.addTab(
                tabTitle,
                null,
                new PieChartPanel(chartService.getJFreeChart(chartTitle, dataset), true),
                tabTitle);
    }

//    private static class StackPanel extends JPanel {
//
//        public StackPanel() {
//            this.setBackground(new Color(255,255,255));
//        }
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            g.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
//
//            g.setColor(Color.GREEN);
//            g.drawRect(10, 20, 350, 25);
//            g.fillRect(10, 20, 100, 25);
//
//            g.setColor(Color.RED);
//            g.drawRect(10, 60, 350, 25);
//            g.fillRect(10, 60, 100, 25);
//        }
//    }
}
