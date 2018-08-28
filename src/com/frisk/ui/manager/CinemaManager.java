package com.frisk.ui.manager;

import com.frisk.MovieException;
import com.frisk.entity.Cinema;
import com.frisk.entity.Movie;
import com.frisk.service.CinemaService;
import com.frisk.service.impl.CinemaServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * User: frisk
 * Date: 2018/7/31
 * Time: 10:17
 */
public class CinemaManager {
    private JTable table1;
    private JTextField textField1;
    private JButton add;
    private JButton delete;
    private JButton alter;
    private JButton find;
    private JPanel panel1;
    private JScrollPane js;
    private JPanel panel2;
    private JPanel panel;

    CinemaService cinemaService = new CinemaServiceImpl();


    public CinemaManager(){
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

        String[] name = {"id ", "电影院名 ", "电影院地址"};
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

        List<Cinema> cinemas = cinemaService.showCinemas();

        for (Cinema cinema : cinemas) {
            defaultModel.addRow(new Object[]{cinema.getId(),cinema.getName(),
                    cinema.getAddress()});
        }
        defaultModel.addRow(new Vector());


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
                String address = null;
                try {
                    name = table1.getValueAt(count, 1).toString();
                    address = table1.getValueAt(count, 2).toString();
                    if ("".equals(name) || "".equals(address)){
                        throw new NullPointerException();
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "电影院名和地址不能为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    cinemaService.addCinema(new Cinema(id,name,address));
                    defaultModel.setRowCount(0);
                    List<Cinema> cinemas = cinemaService.showCinemas();
                    for (Cinema cinema : cinemas) {
                        defaultModel.addRow(new Object[]{cinema.getId(), cinema.getName(), cinema.getAddress()});
                    }
                    defaultModel.addRow(new Vector());
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }

            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取选中的行号（记录）
                int count = table1.getSelectedRow();
                if (count==-1){
                    JOptionPane.showMessageDialog(panel1, "请选中一条记录","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (table1.getRowCount()-1==count){
                    JOptionPane.showMessageDialog(panel1, "此行不可删除","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(table1.getValueAt(count, 0).toString());
                int i = JOptionPane.showConfirmDialog(panel1, "是否删除编号为" + id + "的影院?", "删除提示",
                        JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    cinemaService.deleteCinemaById(id);
                    defaultModel.removeRow(count);
                }
                String name = null;
                String address = null;
                try {
                    name = table1.getValueAt(count, 1).toString();
                    address = table1.getValueAt(count, 2).toString();
                    if ("".equals(name) || "".equals(address)){
                        throw new NullPointerException();
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "电影院名和地址不能为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    cinemaService.addCinema(new Cinema(id,name,address));
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        alter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = table1.getSelectedRow();//获取选中的行号（记录）
                if (count==-1){
                    JOptionPane.showMessageDialog(panel1, "请选中一条记录","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(table1.getValueAt(count, 0).toString());
                String name = null;
                String address = null;
                try {
                    name = table1.getValueAt(count, 1).toString();
                    address = table1.getValueAt(count, 2).toString();
                    if ("".equals(name) || "".equals(address)){
                        throw new NullPointerException();
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "电影院名和地址不能为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                cinemaService.alterCinemaById(new Cinema(id,name,address));
            }
        });
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cinema> cinemas = new ArrayList<>();
                Object[] fruits = {"查询全部","按id查询","按名字查询"};
                String str = "";
                str = (String) JOptionPane.showInputDialog(null, "选择查询方式",
                        "查询", JOptionPane.QUESTION_MESSAGE, null, fruits, fruits[0]);
                if (str == null){
                    return;
                }
                if ("查询全部".equals(str)){
                    cinemas = cinemaService.showCinemas();
                }else if ("按id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        Cinema cinema = cinemaService.findCinemaById(id);
                        cinemas.clear();
                        cinemas.add(cinema);
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
                        cinemas = cinemaService.findCinemaByName(name);
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                defaultModel.setDataVector((Object[][]) null,name);
                for (Cinema cinema : cinemas) {
                    defaultModel.addRow(new Object[]{cinema.getId(), cinema.getName(), cinema.getAddress()});
                }
                defaultModel.addRow(new Vector());
                textField1.setText("输入查询关键字");
            }
        });
    }


    public JPanel getCinemaManager(){
        return panel;
    }
}
