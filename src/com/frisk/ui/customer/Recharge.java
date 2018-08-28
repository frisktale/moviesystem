package com.frisk.ui.customer;

import com.frisk.MovieException;
import com.frisk.entity.User;
import com.frisk.service.UserService;
import com.frisk.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: frisk
 * Date: 2018/8/1
 * Time: 15:36
 */
public class Recharge {
    private JTextField textField2;
    private JButton recharge;
    private JPanel panel;
    User user;
    UserService userService = new UserServiceImpl();

    public Recharge(User user) {
        /*try {
            this.user = userService.findUserById(user.getId());
        } catch (MovieException e) {
            JOptionPane.showMessageDialog(panel, e.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
        }*/
        recharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int money = 0;
                try {
                    if (textField2.getText() == null || "".equals(textField2.getText())) {
                        JOptionPane.showMessageDialog(panel, "充值金额不能为空", "错误", JOptionPane.WARNING_MESSAGE);
                        return;
                    } else {
                        money = Integer.parseInt(textField2.getText());
                    }
                    if (money<0){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel, "充值金额输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    textField2.getText();
                    return;
                }

                user.setMoney(user.getMoney() + money);
                userService.alterUser(user);

                JOptionPane.showMessageDialog(panel, "充值成功", "成功", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    public JPanel getRechargeFrame() {

        return panel;
    }
}
