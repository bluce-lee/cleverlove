package com.cleverlove.viewholder.panel;

import com.cleverlove.base.BaseViewHolder;
import com.cleverlove.base.GBC;
import com.cleverlove.base.SingletonObservable;
import com.cleverlove.view.panel.CodeContentPanel;
import com.cleverlove.view.panel.ContentViewPanel;

import javax.swing.*;
import java.awt.*;

public class ContentViewPanelViewHolder extends BaseViewHolder {

    public JLabel titleLabel;
    public CodeContentPanel codeContentPanel;

    public ContentViewPanelViewHolder(Object object) {
        super(object);
        init((ContentViewPanel) object);
    }

    private void init(ContentViewPanel contentViewPanel) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        contentViewPanel.setLayout(gridBagLayout);
        GBC gbc = null;

        titleLabel = new JLabel();
        titleLabel.setFont(font);
        titleLabel.setBackground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setPreferredSize(new Dimension(50, 40));
        gbc = new GBC(0, 0, 1, 1);
        gbc.setFill(GBC.BOTH)
                .setWeight(1, 0)
                .setIpad(0, 0);
        gridBagLayout.setConstraints(titleLabel, gbc);


        codeContentPanel = new CodeContentPanel();
        codeContentPanel.setPreferredSize(new Dimension(50, 50));
        gbc = new GBC(0, 1, 1, 1);
        gbc.setFill(GBC.BOTH)
                .setWeight(1, 1)
                .setIpad(0, 0);
        gridBagLayout.setConstraints(codeContentPanel, gbc);
        SingletonObservable.getSingletonObservable().addObserver(codeContentPanel);

        contentViewPanel.add(titleLabel);
        contentViewPanel.add(codeContentPanel);
    }
}
