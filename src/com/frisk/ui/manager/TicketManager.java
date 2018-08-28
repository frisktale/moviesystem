package com.frisk.ui.manager;

import com.frisk.MovieException;
import com.frisk.entity.FullSession;
import com.frisk.entity.Movie;
import com.frisk.entity.Ticket;
import com.frisk.service.TicketService;
import com.frisk.service.impl.TicketServiceImpl;
import com.frisk.utils.DateFormatByString;
import com.frisk.utils.impl.DateFormatByStringImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * User: frisk
 * Date: 2018/7/31
 * Time: 18:24
 */
public class TicketManager {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton add;
    private JButton delete;
    private JButton alter;
    private JButton find;
    private JPanel panel2;
    private JScrollPane js;

    DateFormatByString format = new DateFormatByStringImpl();

    TicketService ticketService = new TicketServiceImpl();

    public TicketManager(){
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

        String[] name = {"id ", "用户id ", "场次id","购票时间","座位号"};
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

        List<Ticket> tickets = ticketService.query();
        for (Ticket ticket : tickets) {
            defaultModel.addRow(new Object[]{ticket.getId(),ticket.getuId(),
                    ticket.getsId(),ticket.getTime(),ticket.getSeat()});
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

                int uId = 0;
                try {
                    if (table1.getValueAt(count, 1)==null|| "".equals(table1.getValueAt(count, 1).toString())){
                        JOptionPane.showMessageDialog(panel1, "用户id不能为空", "错误",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }else {
                        uId = Integer.parseInt(table1.getValueAt(count, 1).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "用户id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("",count,1);
                    return;
                }


                int sId = 0;
                try {
                    if (table1.getValueAt(count, 2)==null|| "".equals(table1.getValueAt(count, 2).toString())){
                        JOptionPane.showMessageDialog(panel1, "场次id不能为空", "错误",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }else {
                        sId = Integer.parseInt(table1.getValueAt(count, 2).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("",count,2);
                    return;
                }

                Date time;

                try {
                    String t = table1.getValueAt(count, 3).toString();

                    if ("".equals(t)){
                        throw new NullPointerException();
                    }
                    time = format.format(t);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "购票时间不能为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int seat = 0;
                try {
                    if (table1.getValueAt(count, 4)==null|| "".equals(table1.getValueAt(count, 4).toString())){
                        JOptionPane.showMessageDialog(panel1, "座位号不能为空", "错误",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }else {
                        seat = Integer.parseInt(table1.getValueAt(count, 4).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "座位号输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("",count,4);
                    return;

                }
                try {
                    ticketService.addTicket(new Ticket(id,uId,sId,time,seat));
                    defaultModel.setRowCount(0);
                    List<Ticket> tickets = ticketService.query();
                    for (Ticket ticket : tickets) {
                        defaultModel.addRow(new Object[]{ticket.getId(),ticket.getuId(),
                                ticket.getsId(),ticket.getTime(),ticket.getSeat()});
                    }
                    defaultModel.addRow(new Vector());
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = table1.getSelectedRow();//获取选中的行号（记录）
                if (table1.getRowCount()-1==count){
                    JOptionPane.showMessageDialog(panel1, "此行不可删除","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (count==-1){
                    JOptionPane.showMessageDialog(panel1, "请选中一条记录","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(table1.getValueAt(count, 0).toString());
                int i = JOptionPane.showConfirmDialog(panel1, "是否删除编号为" + id + "的电影票记录?", "删除提示",
                        JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    ticketService.deleteTicket(id);
                    defaultModel.removeRow(count);
                }
            }
        });
        alter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(panel1, "请确保表格不在编辑状态\r\n(即所选行均为深色背景)\n\r", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (i != 0) {
                    return;
                }
                int count = table1.getSelectedRow();//获取选中的行号（记录）
                if (count==-1){
                    JOptionPane.showMessageDialog(panel1, "请选中一条记录","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.parseInt(table1.getValueAt(count, 0).toString());

                int uId = 0;
                try {
                    if (table1.getValueAt(count, 1)==null|| "".equals(table1.getValueAt(count, 1).toString())){
                        JOptionPane.showMessageDialog(panel1, "用户id不能为空", "错误",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }else {
                        uId = Integer.parseInt(table1.getValueAt(count, 1).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "用户id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("",count,1);
                    return;
                }


                int sId = 0;
                try {
                    if (table1.getValueAt(count, 2)==null|| "".equals(table1.getValueAt(count, 2).toString())){
                        JOptionPane.showMessageDialog(panel1, "场次id不能为空", "错误",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }else {
                        sId = Integer.parseInt(table1.getValueAt(count, 2).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("",count,2);
                    return;
                }

                Date time;

                try {
                    String t = table1.getValueAt(count, 3).toString();

                    if ("".equals(t)){
                        throw new NullPointerException();
                    }
                    time = format.format(t);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "购票时间不能为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int seat = 0;
                try {
                    if (table1.getValueAt(count, 4)==null|| "".equals(table1.getValueAt(count, 4).toString())){
                        JOptionPane.showMessageDialog(panel1, "座位号不能为空", "错误",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }else {
                        seat = Integer.parseInt(table1.getValueAt(count, 4).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "座位号输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("",count,4);
                    return;

                }

                try {
                    ticketService.alterTicket(new Ticket(id,uId,sId,time,seat));
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] fruits = {"查询全部","按id查询","按用户id查询","按场次id查询"};
                String str = "";
                str = (String) JOptionPane.showInputDialog(null, "选择查询方式",
                        "查询", JOptionPane.QUESTION_MESSAGE, null, fruits, fruits[0]);
                if (str == null){
                    return;
                }
                List<Ticket> tickets = new ArrayList<>();
                if ("查询全部".equals(str)) {
                    tickets = ticketService.query();
                }else if ("按id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        tickets.clear();
                        tickets.add(ticketService.findTicketById(id));
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel1, "id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                        textField1.setText("");
                        return;
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                } else if ("按用户id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        tickets.clear();
                        tickets=ticketService.findTicketByUid(id);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel1, "id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                        textField1.setText("");
                        return;
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } else if ("按场次id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        tickets.clear();
                        tickets=ticketService.findTicketBySid(id);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel1, "id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                        textField1.setText("");
                        return;
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                defaultModel.setDataVector((Object[][]) null, name);
                for (Ticket ticket : tickets) {
                    defaultModel.addRow(new Object[]{ticket.getId(),ticket.getuId(),
                            ticket.getsId(),ticket.getTime(),ticket.getSeat()});
                }
                defaultModel.addRow(new Vector());
                textField1.setText("输入查询关键字");
            }
        });
    }

    public JPanel getTicketManager() {
        return panel1;
    }
}
