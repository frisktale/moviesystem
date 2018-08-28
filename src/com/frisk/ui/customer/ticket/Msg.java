package com.frisk.ui.customer.ticket;

import javax.swing.*;

/**
 * User: frisk
 * Date: 2018/8/6
 * Time: 9:37
 */
public class Msg extends JFrame {
    public Msg(String msg){
        JTextArea textArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);        //激活自动换行功能
        textArea.setWrapStyleWord(true);            // 激活断行不断字功能
        textArea.setEditable(false);

        textArea.setText(msg);

        this.setTitle("message");
        this.add(jScrollPane);
        this.setVisible(true);
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
    }
}
