package com.cleverlove.viewholder.panel;

import com.cleverlove.base.BaseViewHolder;
import com.cleverlove.base.GBC;
import com.cleverlove.view.panel.CodeFileSelectPanel;

import javax.swing.*;
import java.awt.*;

public class CodeFileSelectPanelViewHolder extends BaseViewHolder {

    private JLabel jLabel;
    private JScrollPane jScrollPane;
    public JList<?> jList;
    public JButton jButton;

    public CodeFileSelectPanelViewHolder(Object object) {
        super(object);
        init((CodeFileSelectPanel) object);
    }

    private void init(CodeFileSelectPanel codeFileSelectPanel) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        codeFileSelectPanel.setLayout(gridBagLayout);
        GBC gbc = null;

        jLabel = new JLabel("代码文件列表");
        jLabel.setHorizontalTextPosition(JLabel.CENTER);
        jLabel.setPreferredSize(new Dimension(50, 30));
        jLabel.setFont(font);
        gbc = new GBC(0, 0, 1, 1);
        gbc.setFill(GBC.BOTH)
                .setWeight(1, 0)
                .setIpad(0, 0);
        gridBagLayout.setConstraints(jLabel, gbc);

        jButton = new JButton("浏览文件");
        jButton.setFont(font);
        jButton.setOpaque(true);
        jButton.setBackground(new Color(10, 175, 250));
        jButton.setFocusable(false);
        jButton.addActionListener(codeFileSelectPanel);
        jButton.setPreferredSize(new Dimension(150, 30));
        gbc = new GBC(1, 0, 1, 1);
        gbc.setFill(GBC.BOTH)
                .setWeight(0, 0)
                .setIpad(0, 0);
        gridBagLayout.setConstraints(jButton, gbc);


        jList = new JList<>();
        jList.setFont(font);
        jList.setDoubleBuffered(true);
        jList.setSelectionBackground(new Color(213, 194, 255));
        jList.setBackground(new Color(255, 255, 255));
        jList.addMouseListener(codeFileSelectPanel);

        jScrollPane = new JScrollPane();
        jScrollPane.setPreferredSize(new Dimension(50, 50));
        jScrollPane.setViewportView(jList);
        gbc = new GBC(0, 1, 2, 1);
        gbc.setFill(GBC.BOTH)
                .setWeight(1, 1)
                .setIpad(0, 0);
        gridBagLayout.setConstraints(jScrollPane, gbc);

        codeFileSelectPanel.add(jLabel);
        codeFileSelectPanel.add(jScrollPane);
        codeFileSelectPanel.add(jButton);
    }
}
