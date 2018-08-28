package com.frisk.ui.manager;

import com.frisk.MovieException;
import com.frisk.entity.*;
import com.frisk.service.*;
import com.frisk.service.impl.*;
import com.frisk.utils.DateFormatByString;
import com.frisk.utils.impl.DateFormatByStringImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.*;

/**
 * User: frisk
 * Date: 2018/7/31
 * Time: 11:10
 */
public class SessionManager {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton add;
    private JButton delete;
    private JButton alter;
    private JButton find;
    private JPanel panel2;
    private JScrollPane js;
    private JTextField textField2;


    SessionService sessionService = new SessionServiceImpl();
    DateFormatByString format = new DateFormatByStringImpl();

    String[] name = {"id ", "电影id ", "电影名", "开始时间", "结束时间", "大厅id",
            "大厅内部编号", "大厅名", "大厅所属影院编号", "大厅所属影院名称", "剩余票数", "票价"};
    DefaultTableModel defaultModel;

    public SessionManager() {
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

        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if ("此处输入结束时间".equals(textField2.getText())) {
                    textField2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(textField2.getText())) {
                    textField2.setText("此处输入结束时间");
                }
            }
        });
        textField2.setText("此处输入结束时间");
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        defaultModel = new DefaultTableModel(null, name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if ((column == 0 && row != table1.getRowCount() - 1) ||
                        (column == 2 || column == 6 || column == 7 || column == 8 || column == 9)) {
                    return false;
                }
                return true;
            }
        };

        table1.getTableHeader().setReorderingAllowed(false);
        table1.setModel(defaultModel);
        /*{"id ", "电影id ", "电影名", "开始时间", "结束时间", "大厅id",
            "大厅内部编号", "大厅名","大厅所属影院编号", "大厅所属影院名称", "剩余票数", "票价"};*/

        List<FullSession> sessions = sessionService.showFullSession();



        for (FullSession session : sessions) {

            defaultModel.addRow(new Object[]{session.getId(), session.getmId()
                    , session.getmName(), session.getsTime(), session.geteTime()
                    , session.gethId(), session.getInsId(), session.gethName()
                    , session.getcId(), session.getcName(), session.getCount()
                    , session.getPrice()});
        }
        defaultModel.addRow(new Vector());
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(panel1,
                        "请确保表格不在编辑状态\r\n(即所选行均为深色背景)\n\r", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (i != 0) {
                    return;
                }
                int count = table1.getRowCount() - 1;
                int id = 0;
                try {
                    if (table1.getValueAt(count, 0) == null || "".equals(table1.getValueAt(count, 0).toString())) {
                        id = 0;
                    } else {
                        id = Integer.parseInt(table1.getValueAt(count, 0).toString());
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "id输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", count, 0);
                    return;
                }

                int mId;
                try {
                    mId = Integer.parseInt(table1.getValueAt(count, 1).toString());

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "电影id输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", count, 1);
                    return;
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "电影id不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Date sTime = null;
                Date eTime = null;
                try {
                    String t1 = table1.getValueAt(count, 3).toString();
                    String t2 = table1.getValueAt(count, 4).toString();
                    if ("".equals(t1) || "".equals(t2)) {
                        throw new NullPointerException();
                    }
                    sTime = format.format(t1);
                    eTime = format.format(t2);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "开始时间和结束时间不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int hId;
                try {
                    hId = Integer.parseInt(table1.getValueAt(count, 5).toString());

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "放映厅id输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", count, 5);
                    return;
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "放映厅id不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int price;
                try {
                    price = Integer.parseInt(table1.getValueAt(count, 11).toString());
                    if (price<0){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "票价输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", count, 10);
                    return;
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "票价不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int tCount;
                try {
                    tCount = Integer.parseInt(table1.getValueAt(count, 10).toString());
                    if (tCount<0){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "票数输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", count, 11);
                    return;
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "票数不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }


                try {
                    sessionService.addSession(new Session(id, mId, sTime, eTime, hId, tCount, price));
                    defaultModel.addRow(new Vector());
                    List<FullSession> sessions = sessionService.showFullSession();
                    defaultModel.setDataVector((Object[][]) null, name);
                    for (FullSession session : sessions) {
                        defaultModel.addRow(new Object[]{session.getId(), session.getmId()
                                , session.getmName(), session.getsTime(), session.geteTime()
                                , session.gethId(), session.getInsId(), session.gethName()
                                , session.getcId(), session.getcName(), session.getCount()
                                , session.getPrice()});
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
                int i = JOptionPane.showConfirmDialog(panel1, "是否删除编号为" + id + "的场次?", "删除提示",
                        JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    sessionService.deleteSession(id);
                    defaultModel.removeRow(count);
                }
            }
        });
        alter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(panel1,
                        "请确保表格不在编辑状态\r\n(即所选行均为深色背景)\n\r", "提示",
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

                int mId;
                try {
                    mId = Integer.parseInt(table1.getValueAt(count, 1).toString());

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "电影id输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", count, 1);
                    return;
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "电影id不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Date sTime = null;
                Date eTime = null;
                try {
                    String t1 = table1.getValueAt(count, 3).toString();
                    String t2 = table1.getValueAt(count, 4).toString();
                    if ("".equals(t1) || "".equals(t2)) {
                        throw new NullPointerException();
                    }
                    sTime = format.format(t1);
                    eTime = format.format(t2);
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "开始时间和结束时间不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int hId;
                try {
                    hId = Integer.parseInt(table1.getValueAt(count, 5).toString());

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "放映厅id输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", count, 5);
                    return;
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "放映厅id不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int price;
                try {
                    price = Integer.parseInt(table1.getValueAt(count, 11).toString());
                    if (price<0){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "票价输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", count, 11);
                    return;
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "票价不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int tCount;
                try {
                    tCount = Integer.parseInt(table1.getValueAt(count, 10).toString());
                    if (tCount<0){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "票数输入错误", "错误", JOptionPane.WARNING_MESSAGE);
                    table1.setValueAt("", count, 10);
                    return;
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "票数不能为空", "错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    sessionService.alterSession(new Session(id, mId, sTime, eTime, hId, tCount, price));
                    defaultModel.addRow(new Vector());
                    List<FullSession> sessions = sessionService.showFullSession();
                    defaultModel.setDataVector((Object[][]) null, name);
                    for (FullSession session : sessions) {
                        defaultModel.addRow(new Object[]{session.getId(), session.getmId()
                                , session.getmName(), session.getsTime(), session.geteTime()
                                , session.gethId(), session.getInsId(), session.gethName()
                                , session.getcId(), session.getcName(), session.getCount()
                                , session.getPrice()});
                    }
                    defaultModel.addRow(new Vector());
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
                Object[] fruits = {"查询全部","按id查询","按电影院id查询","按电影id查询","按放映时间段查询"};
                String str = "";
                str = (String) JOptionPane.showInputDialog(null, "选择查询方式",
                        "查询", JOptionPane.QUESTION_MESSAGE, null, fruits, fruits[0]);
                if (str == null){
                    return;
                }
                List<FullSession> sessions = new ArrayList<>();
                if ("查询全部".equals(str)) {
                    sessions = sessionService.showFullSession();
                }else if ("按id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        sessions.clear();
                        sessions.add(sessionService.findFullSessionById(id));
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel1, "id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                        textField1.setText("");
                        return;
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                } else if ("按电影院id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        sessions.clear();
                        sessions=sessionService.findFullSessionByCinema(id);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel1, "id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                        textField1.setText("");
                        return;
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } else if ("按电影id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        sessions.clear();
                        sessions=sessionService.findFullSessionByMovie(id);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel1, "id输入错误","错误",JOptionPane.WARNING_MESSAGE);
                        textField1.setText("");
                        return;
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } else if ("按放映时间段查询".equals(str)){
                    try {
                        Date sTime = format.format(textField1.getText());
                        Date eTime = format.format(textField2.getText());
                        sessions.clear();
                        sessions=sessionService.findFullSessionByStime(sTime,eTime);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(panel1, "时间输入错误","错误",JOptionPane.WARNING_MESSAGE);
                        textField1.setText("");
                        return;
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                defaultModel.setDataVector((Object[][]) null, name);
                for (FullSession session : sessions) {
                    defaultModel.addRow(new Object[]{session.getId(), session.getmId()
                            , session.getmName(), session.getsTime(), session.geteTime()
                            , session.gethId(), session.getInsId(), session.gethName()
                            , session.getcId(), session.getcName(), session.getCount()
                            , session.getPrice()});
                }
                defaultModel.addRow(new Vector());
                textField1.setText("输入查询关键字");
                textField2.setText("此处输入结束时间");
            }
        });
    }



    public JPanel getSessionManager() {
        return panel1;
    }
}
