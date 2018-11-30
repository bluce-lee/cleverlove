package com.cleverlove.viewholder.panel;

import com.cleverlove.base.BaseViewHolder;
import com.cleverlove.base.GBC;
import com.cleverlove.view.panel.CodeFileSelectPanel;
import com.cleverlove.view.panel.DataViewPanel;
import com.cleverlove.view.panel.TestCaseCodeFileSelectPanel;

import javax.swing.*;
import java.awt.*;

public class DataViewPanelViewHolder extends BaseViewHolder {

    public JLabel jLabel;
    public JTabbedPane filelistJTabbedPane;
    public JTabbedPane chartJTabbedPane;

    public CodeFileSelectPanel codeFileSelectPanel;
    public TestCaseCodeFileSelectPanel testCaseCodeFileSelectPanel;

    public DataViewPanelViewHolder(Object object) {
        super(object);
        init((DataViewPanel) object);
    }

    public void init(DataViewPanel dataViewPanel) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        dataViewPanel.setLayout(gridBagLayout);
        GBC gbc = null;


        filelistJTabbedPane = new JTabbedPane();
        filelistJTabbedPane.setPreferredSize(new Dimension(50, 360));
        gbc = new GBC(0, 0, 1, 1);
        gbc.setFill(GBC.BOTH)
                .setWeight(1, 1)
                .setIpad(0, 0);
        gridBagLayout.setConstraints(filelistJTabbedPane, gbc);

        codeFileSelectPanel = new CodeFileSelectPanel();
        testCaseCodeFileSelectPanel = new TestCaseCodeFileSelectPanel();

        filelistJTabbedPane.add("代码文件列表", codeFileSelectPanel);
        filelistJTabbedPane.add("测试用例文件列表", testCaseCodeFileSelectPanel);

        jLabel = new JLabel();
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setFont(new Font("楷体", Font.CENTER_BASELINE, 25));
        jLabel.setPreferredSize(new Dimension(50, 35));
        gbc = new GBC(0, 1, 1, 1);
        gbc.setFill(GBC.BOTH)
                .setWeight(1, 0)
                .setIpad(0, 0);
        gridBagLayout.setConstraints(jLabel, gbc);


        chartJTabbedPane = new JTabbedPane();
        chartJTabbedPane.setBackground(new Color(255, 255, 255));
        chartJTabbedPane.setPreferredSize(new Dimension(50, 400));
        gbc = new GBC(0, 2, 1, 1);
        gbc.setFill(GBC.BOTH)
                .setWeight(1, 1)
                .setIpad(0, 0);
        gridBagLayout.setConstraints(chartJTabbedPane, gbc);

        dataViewPanel.add(filelistJTabbedPane);
        dataViewPanel.add(jLabel);
        dataViewPanel.add(chartJTabbedPane);
    }
}
