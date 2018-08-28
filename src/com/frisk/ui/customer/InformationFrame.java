package com.frisk.ui.customer;

import com.frisk.entity.User;

import javax.swing.*;

/**
 * User: frisk
 * Date: 2018/8/1
 * Time: 18:41
 */
public class InformationFrame {
    private JLabel id;
    private JLabel name;
    private JLabel money;
    private JPanel panel;

//    private User user;

    public InformationFrame(User user) {
//        this.user = user;
        id.setText(String.valueOf(user.getId()));
        name.setText(user.getName());
        money.setText(String.valueOf(user.getMoney()));
    }

    public JPanel information() {
        return panel;
    }
}
