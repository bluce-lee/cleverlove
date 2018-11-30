package com.cleverlove.view.frame;

import com.cleverlove.view.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

public class MainJFrame extends JFrame {
    private final int WIDTH = 1300, HEIGHT = 850; // 面板的宽高

    public MainJFrame(String title) throws HeadlessException {
        super(title);
        init();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(true); // 是否可调整窗格大小
        this.setLocationRelativeTo(null);
        this.setLocation(300, 100); // 设置窗格在桌面中的位置
        this.setIconImage(Toolkit.getDefaultToolkit().createImage("res/images/logo.png"));// 设置图标
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);
        Container container = this.getContentPane();
        container.setBackground(Color.WHITE);
        MainPanel mainPanel = new MainPanel();
        springLayout.putConstraint(SpringLayout.EAST, mainPanel,0, SpringLayout.EAST, container);
        springLayout.putConstraint(SpringLayout.NORTH, mainPanel,0, SpringLayout.NORTH, container);
        springLayout.putConstraint(SpringLayout.WEST, mainPanel,0, SpringLayout.WEST, container);
        springLayout.putConstraint(SpringLayout.SOUTH, mainPanel,0, SpringLayout.SOUTH, container);
        container.add(mainPanel);
        this.pack(); // 自适应
        this.setVisible(true); // 可见
    }

//    @Override
//    public void update(Observable o, Object arg) {
//        this.getContentPane().remove((Component) arg);
////        this.getContentPane().add(new TablePanel());
//        this.pack(); // 调整大小
////        play.requestFocus(); // 请求聚焦
//        this.repaint(100); // 重绘
////        this.dispose();
//    }
}
