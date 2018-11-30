package com.cleverlove.view.panel;

import com.cleverlove.entity.Msg;
import com.cleverlove.viewholder.panel.ContentViewPanelViewHolder;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ContentViewPanel extends JPanel implements Observer {

    private ContentViewPanelViewHolder contentViewPanelViewHolder;

    public ContentViewPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        this.setBackground(Color.WHITE);
        contentViewPanelViewHolder = new ContentViewPanelViewHolder(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Msg msg = (Msg) arg;
        String filename = msg.getContent().get(Msg.MSG_SINGATURE);
        contentViewPanelViewHolder.titleLabel.setText(filename);
    }
}
