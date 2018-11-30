package com.cleverlove.view.panel;

import com.cleverlove.viewholder.panel.MainPanelViewHolder;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JSplitPane {

    private MainPanelViewHolder mainPanelViewHolder;

    public MainPanel() {
        init();
    }

    public void init() {
        this.setBackground(new Color(255, 255, 255));
        this.setDoubleBuffered(true);
        this.setOrientation(MainPanel.HORIZONTAL_SPLIT);
        mainPanelViewHolder = new MainPanelViewHolder(this);
    }
}
