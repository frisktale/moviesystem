package com.frisk.ui.manager;

import com.frisk.MovieException;
import com.frisk.entity.User;
import com.frisk.service.UserService;
import com.frisk.service.impl.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * User: frisk
 * Date: 2018/7/30
 * Time: 16:23
 */
public class UserManager {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton find;
    private JButton alter;
    private JButton delete;
    private JButton add;
    private JScrollPane js;

    UserService userService = new UserServiceImpl();

    public UserManager() {

        textField1.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                if ("输入查询关键字".equals(textField1.getText())) {
                    textField1.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e)
            {
                if ("".equals(textField1.getText())) {
                    textField1.setText("输入查询关键字");
                }
            }
        });
        textField1.setText("输入查询关键字");

        String[] name = {"id ", "姓名 ", "密码 ", "是否为管理员 ", "余额 "};
        DefaultTableModel defaultModel = new DefaultTableModel(null, name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0&&row!=table1.getRowCount()-1) {
                    return false;
                }
                return true;
            }
        };
        table1.getTableHeader().setReorderingAllowed(false);
        table1.setModel(defaultModel);
        List<User> users = userService.showUser();
        for (User user : users) {
            defaultModel.addRow(new Object[]{user.getId(), user.getName(), user.getPassword(),
                    (user.getStatus() == 1 ? "是" : "否"), user.getMoney()});
        }
        defaultModel.addRow(new Vector());




        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = table1.getSelectedRow();//获取选中的行号（记录）
                if (count==-1){
                    JOptionPane.showMessageDialog(panel1, "请选中一条记录","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (table1.getRowCount()-1==count){
                    JOptionPane.showMessageDialog(panel1, "此行不可删除","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(table1.getValueAt(count, 0).toString());
                int i = JOptionPane.showConfirmDialog(panel1, "是否删除编号为" + id + "的用户?", "删除提示",
                        JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    userService.deleteUser(id);
                    defaultModel.removeRow(count);
                }
            }
        });

        alter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = table1.getSelectedRow();//获取选中的行号（记录）
                if (table1.getRowCount()-1==count){
                    JOptionPane.showMessageDialog(panel1, "此行不可删除","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(table1.getValueAt(count, 0).toString());
                /*String name = table1.getValueAt(count, 1).toString();
                String password = table1.getValueAt(count, 2).toString();
                int status = -1;
                if (!(table1.getValueAt(count,3).toString().equals("是")||
                        table1.getValueAt(count,3).toString().equals("否"))) {
                    status = (table1.getValueAt(count,3).toString().equals("是")?1:-1);
                }else {
                    JOptionPane.showMessageDialog(panel1, "身份输入错误\n\r,仅可输入是/否","错误",JOptionPane.WARNING_MESSAGE);
                }
                int money = 0;
                try {
                    if (table1.getValueAt(count, 0).toString().equals("")){
                        money = 0;
                    }else {
                        money = Integer.parseInt(table1.getValueAt(count,4).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "余额输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("",count,0);
                }*/


                String name = null;
                String password = null;
                try {
                    name = table1.getValueAt(count, 1).toString();
                    password = table1.getValueAt(count, 2).toString();
                    if ("".equals(name) || "".equals(password)){
                        throw new NullPointerException();
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "用户名和密码不能为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int status = -1;
                try {
                    if (("是".equals(table1.getValueAt(count, 3).toString()) ||
                            "否".equals(table1.getValueAt(count, 3).toString()))) {
                        status = ("是".equals(table1.getValueAt(count, 3).toString()) ?1:-1);
                    }else {
                        JOptionPane.showMessageDialog(panel1, "身份输入错误\n\r,仅可输入是/否","错误",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "标识不可为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int money = 0;
                try {
                    if (table1.getValueAt(count, 4)==null|| "".equals(table1.getValueAt(count, 4).toString())){
                        money = 0;
                    }else {
                        money = Integer.parseInt(table1.getValueAt(count,4).toString());
                    }
                    if (money<0){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "余额输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("",count,4);
                    return;
                }

                int i = JOptionPane.showConfirmDialog(panel1, "请确保表格不在编辑状态\r\n(即所选行均为深色背景)\n\r" +
                                "是否修改编号为" + id + "的用户的信息?", "删除提示",
                        JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    userService.alterUser(new User(id,name,password,status,money));
                }
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(panel1, "请确保表格不在编辑状态\r\n(即所选行均为深色背景)\n\r", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (i != 0) {
                    return;
                }
                int count = table1.getRowCount()-1;

                int id = 0;
                try {
                    if (table1.getValueAt(count, 0)==null|| "".equals(table1.getValueAt(count, 0).toString())){
                        id = 0;
                    }else {
                        id = Integer.parseInt(table1.getValueAt(count, 0).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("",count,0);
                    return;
                }
                String name = null;
                String password = null;
                try {
                    name = table1.getValueAt(count, 1).toString();
                    password = table1.getValueAt(count, 2).toString();
                    if ("".equals(name) || "".equals(password)){
                        throw new NullPointerException();
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "用户名和密码不能为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int status = -1;
                try {
                    if (("是".equals(table1.getValueAt(count, 3).toString()) ||
                            "否".equals(table1.getValueAt(count, 3).toString()))) {
                        status = ("是".equals(table1.getValueAt(count, 3).toString()) ?1:-1);
                    }else {
                        JOptionPane.showMessageDialog(panel1, "身份输入错误\n\r,仅可输入是/否","错误",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "标识不可为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int money = 0;
                try {
                    if (table1.getValueAt(count, 4)==null|| "".equals(table1.getValueAt(count, 4).toString())){
                        money = 0;
                    }else {
                        money = Integer.parseInt(table1.getValueAt(count,4).toString());
                    }
                    if (money<0){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "余额输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("",count,4);
                    return;
                }

                try {
                    userService.addUser(new User(id,name,password,status,money));
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                defaultModel.addRow(new Vector());
            }
        });

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<User> users = new ArrayList<>();
                Object[] fruits = {"查询全部","按id查询","按名字查询"};
                String str = "";
                str = (String) JOptionPane.showInputDialog(null, "选择查询方式",
                        "查询", JOptionPane.QUESTION_MESSAGE, null, fruits, fruits[0]);
                if (str == null){
                    return;
                }
                if ("查询全部".equals(str)){
                    users = userService.showUser();
                }else if ("按id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        User user = userService.findUserById(id);
                        users.clear();
                        users.add(user);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel1, "id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                        textField1.setText("");
                        return;
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }else if ("按名字查询".equals(str)){
                    String name = textField1.getText();
                    try {
                        users = userService.findUserByName(name);
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                defaultModel.setDataVector((Object[][]) null,name);
                for (User user : users) {
                    defaultModel.addRow(new Object[]{user.getId(), user.getName(), user.getPassword(),
                            (user.getStatus() == 1 ? "是" : "否"), user.getMoney()});
                }
                defaultModel.addRow(new Vector());
                textField1.setText("输入查询关键字");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UserManager");
        frame.setContentPane(new UserManager().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel userManager() {
        return this.panel1;
    }
}
