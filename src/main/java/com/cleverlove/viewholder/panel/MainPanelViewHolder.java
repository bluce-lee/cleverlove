package com.cleverlove.viewholder.panel;

import com.cleverlove.base.BaseViewHolder;
import com.cleverlove.base.SingletonObservable;
import com.cleverlove.view.panel.ContentViewPanel;
import com.cleverlove.view.panel.DataViewPanel;
import com.cleverlove.view.panel.MainPanel;

import java.awt.*;

public class MainPanelViewHolder extends BaseViewHolder {
    public ContentViewPanel contentViewPanel;
    public DataViewPanel dataViewPanel;

    public MainPanelViewHolder(Object object) {
        super(object);
        init((MainPanel) object);
    }

    public void init(MainPanel mainPanel) {
        mainPanel.setResizeWeight(0.9);
        mainPanel.setDividerSize(10);
        mainPanel.setDividerLocation(750);
        mainPanel.setOneTouchExpandable(true);
        mainPanel.setContinuousLayout(true);

        contentViewPanel = new ContentViewPanel(true);
        contentViewPanel.setPreferredSize(new Dimension(750, 800));
        SingletonObservable.getSingletonObservable().addObserver(contentViewPanel);
        mainPanel.setLeftComponent(contentViewPanel);

        dataViewPanel = new DataViewPanel(true);
        dataViewPanel.setPreferredSize(new Dimension(500, 800));
        SingletonObservable.getSingletonObservable().addObserver(dataViewPanel);
        mainPanel.setRightComponent(dataViewPanel);
    }
}
