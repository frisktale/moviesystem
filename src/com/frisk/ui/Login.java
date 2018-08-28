package com.frisk.ui;

import com.frisk.MovieException;
import com.frisk.entity.User;
import com.frisk.service.UserService;
import com.frisk.service.impl.UserServiceImpl;
import com.frisk.ui.customer.CustomerMainTheme;
import com.frisk.ui.manager.ManagerMainTheme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: frisk
 * Date: 2018/7/30
 * Time: 14:56
 */
class Login{
    private JTextField username;
    private JPanel loginPanel;
    private JButton login;
    private JButton register;
    private JPasswordField password;

    UserService userService = new UserServiceImpl();

    public Login(){
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if ("".equals(username.getText()) ||username.getText()==null){
                        JOptionPane.showMessageDialog(loginPanel, "请输入用户名",
                                "错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    User user = userService.login(username.getText(), String.valueOf(password.getPassword()));
                    if (user.getStatus()==1){
                        int i = JOptionPane.showConfirmDialog(loginPanel, "是否进入管理员界面",
                                "管理员你好", JOptionPane.YES_NO_OPTION);
                        if (i == 0) {
                            new ManagerMainTheme().admin();
//                            管理员界面
                        }else if (i==1){
                            new CustomerMainTheme(user).customer();
//                            用户界面
                        }
//                        点X不进入任何界面
                    }else {
//                        用户界面
                        new CustomerMainTheme(user).customer();
                    }
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(loginPanel, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                }finally {
                    password.setText("");
                }
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("".equals(username.getText()) ||username.getText()==null){
                    JOptionPane.showMessageDialog(loginPanel, "请输入用户名",
                            "错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ("".equals(String.valueOf(password.getPassword())) ||password.getPassword()==null){
                    JOptionPane.showMessageDialog(loginPanel, "请输入密码",
                            "错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                JPasswordField pw = new JPasswordField();

                Object[] message = {"输入密码:", pw};

                int res = JOptionPane.showConfirmDialog(loginPanel, message, "请再次输入密码",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res!=0){
                    return;
                }
                if (!String.valueOf(pw.getPassword()).equals(String.valueOf(password.getPassword()))){
                    JOptionPane.showMessageDialog(loginPanel, "注册失败,请确保两次密码相同",
                            "错误",JOptionPane.WARNING_MESSAGE);
                }else {
                    try {
                        userService.register(username.getText(), String.valueOf(password.getPassword()));
                        JOptionPane.showMessageDialog(loginPanel, "注册成功","成功",JOptionPane.PLAIN_MESSAGE);
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(loginPanel, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    public void loginFrame() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Login().loginFrame();
    }
}
