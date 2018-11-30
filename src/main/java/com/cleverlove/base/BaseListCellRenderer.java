package com.cleverlove.base;

import javax.swing.*;
import java.awt.*;

public class BaseListCellRenderer extends JCheckBox implements ListCellRenderer {
    public BaseListCellRenderer() {
        setOpaque(true);
        setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        setPreferredSize(new Dimension(450, 30));
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setText(value.toString());
        if (isSelected) {
            setBackground(new Color(241, 149, 201));
        } else {
            setBackground(new Color(241, 95, 71));
        }
        if (cellHasFocus) {
            setForeground(new Color(50, 59, 255));
        } else {
            setForeground(new Color(0, 0, 0));
        }
        return this;
    }
}
