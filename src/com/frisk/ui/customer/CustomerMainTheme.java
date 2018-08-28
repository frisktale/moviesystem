package com.frisk.ui.customer;

import com.frisk.entity.User;
import com.frisk.ui.customer.ticket.MovieList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: frisk
 * Date: 2018/7/30
 * Time: 16:08
 */
public class CustomerMainTheme {
    private JComboBox comboBox1;
    private JPanel customerPanel;
    private JPanel panel2;
    User user;


    public CustomerMainTheme(User user) {
        this.user = user;
        JPanel movieList = new MovieList(user).getMovieList();
//        要加上布局才能添加控件
        panel2.setLayout(new BorderLayout());

        panel2.add(movieList);
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("购票".equals(comboBox1.getSelectedItem())) {
                    panel2.removeAll();
                    JPanel movieList = new MovieList(user).getMovieList();
                    panel2.add(movieList);
                    panel2.updateUI();

                } else if ("充值".equals(comboBox1.getSelectedItem())) {
                    panel2.removeAll();
                    JPanel recharge = new Recharge(user).getRechargeFrame();
                    panel2.add(recharge);
                    panel2.updateUI();

                } else if ("查看已购电影票".equals(comboBox1.getSelectedItem())) {
                    panel2.removeAll();
                    JPanel boughtTicket = new BoughtTicket(user).getBoughtTicket();
                    panel2.add(boughtTicket);
                    panel2.updateUI();
                } else if (("评论".equals(comboBox1.getSelectedItem()))) {
                    panel2.removeAll();
                    JPanel cCommentFrame = new CustomerCommentFrame(user).getCCommentFrame();
                    panel2.add(cCommentFrame);
                    panel2.updateUI();
                } else if (("个人信息".equals(comboBox1.getSelectedItem()))) {
                    panel2.removeAll();
                    JPanel information = new InformationFrame(user).information();
                    panel2.add(information);
                    panel2.updateUI();
                }
            }
        });
    }

    public void customer() {
//        new CustomerMainTheme();
        JFrame frame = new JFrame("CustomerMainTheme");
        frame.setContentPane(new CustomerMainTheme(user).customerPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

}
