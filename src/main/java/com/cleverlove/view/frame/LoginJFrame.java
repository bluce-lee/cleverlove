package com.cleverlove.view.frame;


import com.cleverlove.entity.User;
import com.cleverlove.service.UserService;
import com.cleverlove.service.UserServiceImpl;
import com.cleverlove.viewholder.frame.LoginJFrameViewHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJFrame extends JFrame implements ActionListener {
    public final int WIDTH = 555, HEIGHT = 420; // 面板的宽高
    private LoginJFrameViewHolder loginJFrameViewHolder;
    private UserService userService = new UserServiceImpl();
    public SpringLayout springLayout = new SpringLayout();
    public Container container;

    public LoginJFrame(String title) throws HeadlessException {
        super(title);
        init();
    }

    private void init() {
        container = this.getContentPane();
        container.setBackground(new Color(40, 39, 34, 93));
        loginJFrameViewHolder = new LoginJFrameViewHolder(this);
        User user = userService.loadCache();
        if (user != null) {
            loginJFrameViewHolder.remeberPwdCheckBox.setSelected(true);
            loginJFrameViewHolder.usernameTextField.setText(user.getName());
            loginJFrameViewHolder.passwordTextField.setText(user.getPassword());
        }
        this.setLayout(springLayout);
        this.setIconImage(Toolkit.getDefaultToolkit().createImage("res/images/logo.png"));// 设置图标
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLocation(650, 250); // 设置窗格在桌面中的位置
        this.pack(); // 自适应
        this.setVisible(true); // 可见
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginJFrameViewHolder.loginButton) {
            loginJFrameViewHolder.loginButton.setEnabled(false);
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

            User user = new User();
            user.setName(loginJFrameViewHolder.usernameTextField.getText());
            user.setPassword(loginJFrameViewHolder.passwordTextField.getText());
            boolean isSuccess = userService.login(user);
            if (isSuccess) {
                if (loginJFrameViewHolder.remeberPwdCheckBox.isSelected()) {
                    userService.cacheUserInfo(user);
                } else {
                    userService.clearCacheUserInfo();
                }
                this.dispose();
                new MainJFrame("clearlove以太坊智能合约自动化测试系统");
            } else {
                JOptionPane.showMessageDialog(this, "用户名或密码错误");
                loginJFrameViewHolder.loginButton.setEnabled(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ImageIcon imageIcon = new ImageIcon("res/images/logo.png");
        imageIcon.paintIcon(this, g, 160, 80);
    }
}
