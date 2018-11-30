package com.cleverlove.viewholder.panel;

import com.cleverlove.base.BaseViewHolder;
import com.cleverlove.view.panel.CodeContentPanel;

import javax.swing.*;
import java.awt.*;

public class CodeContentPanelViewHolder extends BaseViewHolder {
    private Font font = new Font("宋体", Font.CENTER_BASELINE, 25); // 设置字体
    public JTextArea jTextArea;

    public CodeContentPanelViewHolder(Object object) {
        super(object);
        init((CodeContentPanel) object);
    }

    private void init(CodeContentPanel codeContentPanel) {
        jTextArea = new JTextArea();
        jTextArea.setSelectedTextColor(new Color(255, 255, 255));
        jTextArea.setSelectionColor(new Color(12, 141, 240));
        jTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        jTextArea.setEditable(false);
        jTextArea.setDoubleBuffered(true);
        jTextArea.setTabSize(4);
        codeContentPanel.setViewportView(jTextArea);
//        jTextArea.setLineWrap(true);
//        codeContentPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
