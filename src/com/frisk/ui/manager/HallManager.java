package com.frisk.ui.manager;

import com.frisk.MovieException;
import com.frisk.entity.Hall;
import com.frisk.entity.Movie;
import com.frisk.service.HallService;
import com.frisk.service.impl.HallServiceImpl;

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
 * Date: 2018/8/1
 * Time: 9:37
 */
public class HallManager {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton add;
    private JButton delete;
    private JButton alter;
    private JButton find;

    HallService hallService = new HallServiceImpl();

    public HallManager() {


        String[] name = {"id ", "所属电影院 ", "座位数", "大厅名称", "在所属电影院中的编号"};
        DefaultTableModel defaultModel = new DefaultTableModel(null, name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 && row != table1.getRowCount() - 1) {
                    return false;
                }
                return true;
            }
        };
        table1.getTableHeader().setReorderingAllowed(false);
        table1.setModel(defaultModel);

        List<Hall> halls = hallService.showHall();
        for (Hall hall : halls) {
            defaultModel.addRow(new Object[]{hall.getId(), hall.getcId(), hall.getCount(), hall.getName(), hall.gethId()});
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
                int row = table1.getRowCount() - 1;

                int id;
                try {
                    if (table1.getValueAt(row, 0) == null || "".equals(table1.getValueAt(row, 0).toString())) {
                        id = 0;
                    } else {
                        id = Integer.parseInt(table1.getValueAt(row, 0).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "id输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", row, 0);
                    return;
                }

                int cId = 0;
                try {
                    if (table1.getValueAt(row, 1) == null || "".equals(table1.getValueAt(row, 1).toString())) {
                        JOptionPane.showMessageDialog(panel1, "影院id不能为空", "错误", JOptionPane.WARNING_MESSAGE);
                        table1.setValueAt("", row, 1);
                        return;
                    } else {
                        cId = Integer.parseInt(table1.getValueAt(row, 1).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "影院id输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", row, 1);
                    return;
                }

                int count = 0;
                try {
                    if (table1.getValueAt(row, 2) == null || "".equals(table1.getValueAt(row, 2).toString())) {
                        JOptionPane.showMessageDialog(panel1, "座位数输入不能为空", "错误", JOptionPane.WARNING_MESSAGE);
                        table1.setValueAt("", row, 2);
                        return;
                    } else {
                        count = Integer.parseInt(table1.getValueAt(row, 2).toString());
                    }
                    if (count<0){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "座位数输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", row, 2);
                    return;
                }
                String name = null;
                try {
                    name = table1.getValueAt(row, 3).toString();
                    if ("".equals(name)) {
                        throw new NullPointerException();
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "厅名不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int hId = 0;
                try {
                    if (table1.getValueAt(row, 4) == null || "".equals(table1.getValueAt(row, 4).toString())) {
                        JOptionPane.showMessageDialog(panel1, "座位数输入不能为空", "错误", JOptionPane.WARNING_MESSAGE);
                        table1.setValueAt("", row, 4);
                        return;
                    } else {
                        hId = Integer.parseInt(table1.getValueAt(row, 4).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "座位数输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", row, 4);
                    return;
                }

                try {
                    hallService.addHall(new Hall(id, cId, count, hId, name));
                    defaultModel.setRowCount(0);
                    List<Hall> halls = hallService.showHall();
                    for (Hall hall : halls) {
                        defaultModel.addRow(new Object[]{hall.getId(), hall.getcId(), hall.getCount(), hall.getName(), hall.gethId()});
                    }
                    defaultModel.addRow(new Vector());
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if ("输入查询关键字".equals(textField1.getText())) {
                    textField1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(textField1.getText())) {
                    textField1.setText("输入查询关键字");
                }
            }
        });
        textField1.setText("输入查询关键字");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = table1.getSelectedRow();//获取选中的行号（记录）
                if (table1.getRowCount() - 1 == count) {
                    JOptionPane.showMessageDialog(panel1, "此行不可删除", "错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (count==-1){
                    JOptionPane.showMessageDialog(panel1, "请选中一条记录","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(table1.getValueAt(count, 0).toString());
                int i = JOptionPane.showConfirmDialog(panel1, "是否删除编号为" + id + "的放映厅?", "删除提示",
                        JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    hallService.deleteHall(id);
                    defaultModel.removeRow(count);
                }
            }
        });
        alter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();//获取选中的行号（记录）

                int i = JOptionPane.showConfirmDialog(panel1, "请确保表格不在编辑状态\r\n(即所选行均为深色背景)\n\r", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (i != 0) {
                    return;
                }

                if (row==-1){
                    JOptionPane.showMessageDialog(panel1, "请选中一条记录","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(table1.getValueAt(row, 0).toString());

                int cId;
                try {
                    if (table1.getValueAt(row, 1) == null || "".equals(table1.getValueAt(row, 1).toString())) {
                        JOptionPane.showMessageDialog(panel1, "影院id不能为空", "错误", JOptionPane.WARNING_MESSAGE);
                        table1.setValueAt("", row, 1);
                        return;
                    } else {
                        cId = Integer.parseInt(table1.getValueAt(row, 1).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "影院id输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", row, 1);
                    return;
                }

                int count;
                try {
                    if (table1.getValueAt(row, 2) == null || "".equals(table1.getValueAt(row, 2).toString())) {
                        JOptionPane.showMessageDialog(panel1, "座位数不能为空", "错误", JOptionPane.WARNING_MESSAGE);
                        table1.setValueAt("", row, 2);
                        return;
                    } else {
                        count = Integer.parseInt(table1.getValueAt(row, 2).toString());
                    }
                    if (count<0){
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "座位数输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", row, 2);
                    return;
                }
                String name;
                try {
                    name = table1.getValueAt(row, 3).toString();
                    if ("".equals(name)) {
                        throw new NullPointerException();
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "厅名不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int hId;
                try {
                    if (table1.getValueAt(row, 4) == null || "".equals(table1.getValueAt(row, 4).toString())) {
                        JOptionPane.showMessageDialog(panel1, "厅号不能为空", "错误", JOptionPane.WARNING_MESSAGE);
                        table1.setValueAt("", row, 4);
                        return;
                    } else {
                        hId = Integer.parseInt(table1.getValueAt(row, 4).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "厅号输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", row, 4);
                    return;
                }

                try {
                    hallService.alterHall(new Hall(id, cId, count, hId, name));
                    defaultModel.setRowCount(0);
                    List<Hall> halls = hallService.showHall();
                    for (Hall hall : halls) {
                        defaultModel.addRow(new Object[]{hall.getId(), hall.getcId(), hall.getCount(), hall.getName(), hall.gethId()});
                    }
                    defaultModel.addRow(new Vector());
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Hall> halls = new ArrayList<>();
                Object[] fruits = {"查询全部","按id查询","按电影院id查询"};
                String str = "";
                str = (String) JOptionPane.showInputDialog(null, "选择查询方式",
                        "查询", JOptionPane.QUESTION_MESSAGE, null, fruits, fruits[0]);
                if (str == null){
                    return;
                }

                if ("查询全部".equals(str)){
                    halls = hallService.showHall();
                }else if ("按id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        Hall hall = hallService.findHallById(id);
                        halls.clear();
                        halls.add(hall);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel1, "id输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                        textField1.setText("");
                        return;
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                }else if ("按电影院id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        halls = hallService.findHallByCinema(id);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel1, "id输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                        textField1.setText("");
                        return;
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                }
                defaultModel.setRowCount(0);
                for (Hall hall : halls) {
                    defaultModel.addRow(new Object[]{hall.getId(), hall.getcId(), hall.getCount(), hall.getName(), hall.gethId()});
                }
                defaultModel.addRow(new Vector());
            }
        });
    }

    public JPanel getHallManager() {
        return panel1;
    }
}
