package com.cleverlove.viewholder.frame;

import com.cleverlove.base.BaseViewHolder;
import com.cleverlove.view.frame.LoginJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginJFrameViewHolder extends BaseViewHolder {

    public JLabel usernameLabel;
    public JLabel passwordLabel;
    public JTextField usernameTextField;
    public JPasswordField passwordTextField;
    public JButton loginButton;
    public JCheckBox remeberPwdCheckBox;
    public JCheckBox autoLoginCheckBox;

    public LoginJFrameViewHolder(Object object) {
        super(object);
        init((LoginJFrame)object);
    }

    private void init(LoginJFrame jFrame){
        Container container = jFrame.container;
        SpringLayout springLayout = jFrame.springLayout;
        Spring spring = null;

        this.usernameTextField = new JTextField(1);
        this.usernameTextField.setPreferredSize(new Dimension(250, 40));
        this.usernameTextField.setFont(font);
        spring = Spring.constant(50, 165, 800);
        springLayout.putConstraint(SpringLayout.WEST, this.usernameTextField, spring, SpringLayout.WEST, container);
        spring = Spring.constant(80, 180, 800);
        springLayout.putConstraint(SpringLayout.NORTH, this.usernameTextField, spring, SpringLayout.NORTH, container);
        spring = Spring.constant(-130);
        springLayout.putConstraint(SpringLayout.EAST, this.usernameTextField, spring, SpringLayout.EAST, container);

        this.passwordTextField = new JPasswordField(1);
        this.passwordTextField.setPreferredSize(new Dimension(250, 40));
        this.passwordTextField.setFont(new Font("宋体", Font.CENTER_BASELINE, 25));
        spring = Spring.constant(0);
        springLayout.putConstraint(SpringLayout.WEST, this.passwordTextField, spring, SpringLayout.WEST, this.usernameTextField);
        spring = Spring.constant(1);
        springLayout.putConstraint(SpringLayout.NORTH, this.passwordTextField, spring, SpringLayout.SOUTH, this.usernameTextField);
        spring = Spring.constant(0);
        springLayout.putConstraint(SpringLayout.EAST, this.passwordTextField, spring, SpringLayout.EAST, this.usernameTextField);

        this.usernameLabel = new JLabel("用户名");
        this.usernameLabel.setFont(font);
        this.usernameLabel.setPreferredSize(new Dimension(80, 25));
        spring = Spring.constant(85);
        springLayout.putConstraint(SpringLayout.WEST, this.usernameLabel, spring, SpringLayout.WEST, container);
        spring = Spring.constant(7);
        springLayout.putConstraint(SpringLayout.NORTH, this.usernameLabel, spring, SpringLayout.NORTH, this.usernameTextField);
        spring = Spring.constant(0);
        springLayout.putConstraint(SpringLayout.EAST, this.usernameLabel, spring, SpringLayout.EAST, this.usernameTextField);

        this.passwordLabel = new JLabel("密码");
        this.passwordLabel.setFont(font);
        this.passwordLabel.setPreferredSize(new Dimension(80, 25));
        spring = Spring.constant(7);
        springLayout.putConstraint(SpringLayout.NORTH, this.passwordLabel, spring, SpringLayout.NORTH, this.passwordTextField);
        spring = Spring.constant(0);
        springLayout.putConstraint(SpringLayout.EAST, this.passwordLabel, spring, SpringLayout.WEST, this.passwordTextField);

        this.remeberPwdCheckBox = new JCheckBox("记住密码");
        this.remeberPwdCheckBox.setForeground(Color.LIGHT_GRAY);
        this.remeberPwdCheckBox.setFocusPainted(false);
        this.remeberPwdCheckBox.setOpaque(false);
        this.remeberPwdCheckBox.setPreferredSize(new Dimension(80, 20));
        spring = Spring.constant(5);
        springLayout.putConstraint(SpringLayout.NORTH, this.remeberPwdCheckBox, spring, SpringLayout.SOUTH, this.passwordTextField);
        spring = Spring.constant(0);
        springLayout.putConstraint(SpringLayout.WEST, this.remeberPwdCheckBox, spring, SpringLayout.WEST, this.passwordTextField);

        this.autoLoginCheckBox = new JCheckBox("自动登录");
        this.autoLoginCheckBox.setForeground(Color.LIGHT_GRAY);
        this.autoLoginCheckBox.setOpaque(false);
        this.autoLoginCheckBox.setFocusPainted(false);
        this.autoLoginCheckBox.setPreferredSize(new Dimension(80, 20));
        spring = Spring.constant(5);
        springLayout.putConstraint(SpringLayout.NORTH, this.autoLoginCheckBox, spring, SpringLayout.SOUTH, this.passwordTextField);
        spring = Spring.constant(0);
        springLayout.putConstraint(SpringLayout.EAST, this.autoLoginCheckBox, spring, SpringLayout.EAST, this.passwordTextField);

        this.loginButton = new JButton("登 录");
        this.loginButton.setBackground(new Color(0,102,255));
        this.loginButton.setForeground(Color.WHITE);
        this.loginButton.setFocusPainted(false);
        this.loginButton.setFont(font);
        this.loginButton.setPreferredSize(new Dimension(250, 40));
        this.loginButton.addActionListener((ActionListener) jFrame);
        spring = Spring.constant(40);
        springLayout.putConstraint(SpringLayout.NORTH, this.loginButton, spring, SpringLayout.SOUTH, this.passwordTextField);
        spring = Spring.constant(0);
        springLayout.putConstraint(SpringLayout.WEST, this.loginButton, spring, SpringLayout.WEST, this.passwordTextField);
        spring = Spring.constant(0);
        springLayout.putConstraint(SpringLayout.EAST, this.loginButton, spring, SpringLayout.EAST, this.passwordTextField);

        container.add(this.usernameLabel);
        container.add(this.passwordLabel);
        container.add(this.usernameTextField);
        container.add(this.passwordTextField);
        container.add(this.remeberPwdCheckBox);
        container.add(this.autoLoginCheckBox);
        container.add(this.loginButton);
    }
}
