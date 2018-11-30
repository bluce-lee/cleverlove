package com.cleverlove.view.panel;

import com.cleverlove.base.BaseJScrollPane;
import com.cleverlove.entity.Msg;
import com.cleverlove.viewholder.panel.CodeContentPanelViewHolder;

import java.awt.*;
import java.util.Observable;

public class CodeContentPanel extends BaseJScrollPane {

    private CodeContentPanelViewHolder codeContentPanelViewHolder;

    public CodeContentPanel() {
        this.setWheelScrollingEnabled(true);
        this.setBackground(new Color(254, 222, 244, 248));
        codeContentPanelViewHolder = new CodeContentPanelViewHolder(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Msg msg = (Msg) arg;
        String content = msg.getContent().get(Msg.MSG_CONTENT);
        this.codeContentPanelViewHolder.jTextArea.setText(content);
//        this.getVerticalScrollBar().setValueIsAdjusting(true);
//        this.getVerticalScrollBar().setValue(1);
//        this.repaint();
    }
}
