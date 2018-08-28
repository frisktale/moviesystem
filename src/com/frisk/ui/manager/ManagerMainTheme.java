package com.frisk.ui.manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: frisk
 * Date: 2018/7/30
 * Time: 16:08
 */
public class ManagerMainTheme {
    private JComboBox comboBox1;
    private JPanel managerPanel;
    private JPanel panel2;

    public ManagerMainTheme(){
        UserManager userManager = new UserManager();
//        要加上布局才能添加控件
        panel2.setLayout(new BorderLayout());
        panel2.add(userManager.userManager());



        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("用户管理".equals(comboBox1.getSelectedItem())){
                    panel2.removeAll();
                    panel2.add(userManager.userManager());
                    panel2.updateUI();
                }else if ("电影管理".equals(comboBox1.getSelectedItem())){
                    JPanel movieManager = new MovieManager().getMovieManager();
                    panel2.removeAll();
                    panel2.add(movieManager);
                    panel2.updateUI();
                }else if ("影院管理".equals(comboBox1.getSelectedItem())){
                    JPanel cinemaManager = new CinemaManager().getCinemaManager();
                    panel2.removeAll();
                    panel2.add(cinemaManager);
                    panel2.updateUI();
                } else if (("场次管理".equals(comboBox1.getSelectedItem()))){
                    JPanel sessionManager = new SessionManager().getSessionManager();
                    panel2.removeAll();
                    panel2.add(sessionManager);
                    panel2.updateUI();
                } else if (("电影票管理".equals(comboBox1.getSelectedItem()))){
                    JPanel ticketManager = new TicketManager().getTicketManager();
                    panel2.removeAll();
                    panel2.add(ticketManager);
                    panel2.updateUI();
                } else if (("评论管理".equals(comboBox1.getSelectedItem()))){
                    JPanel commentManager = new CommentManager().getCommentManager();
                    panel2.removeAll();
                    panel2.add(commentManager);
                    panel2.updateUI();
                }else if (("放映厅管理".equals(comboBox1.getSelectedItem()))){
                    JPanel hallManager = new HallManager().getHallManager();
                    panel2.removeAll();
                    panel2.add(hallManager);
                    panel2.updateUI();
                }
            }
        });
    }

    public  void admin() {
//        new CustomerMainTheme();
        JFrame frame = new JFrame("CustomerMainTheme");
        frame.setContentPane(new ManagerMainTheme().managerPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}
