package com.frisk.ui.customer.ticket;

import com.frisk.MovieException;
import com.frisk.entity.FullSession;
import com.frisk.entity.User;
import com.frisk.service.SessionService;
import com.frisk.service.TicketService;
import com.frisk.service.impl.SessionServiceImpl;
import com.frisk.service.impl.TicketServiceImpl;
import com.frisk.ui.customer.Hidden;
import com.frisk.utils.DateFormatByString;
import com.frisk.utils.impl.DateFormatByStringImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/8/1
 * Time: 13:15
 */
public class SessionFrame {
    private JPanel panel1;
    private JTable table1;
    private JScrollPane js;
    private JTextField textField1;
    private JTextField textField2;
    private JButton search;
    private JButton buy;
    private User user;

    DateFormatByString format = new DateFormatByStringImpl();
    List<FullSession> fullSessions;
    String[] name = {"id ",  "电影名", "开始时间", "结束时间",
            "大厅内部编号", "大厅名",  "大厅所属影院名称", "剩余票数", "票价"};
    DefaultTableModel defaultModel;
    public SessionFrame(User user, List<FullSession> fullSessions){


        this.fullSessions = fullSessions;
        this.user = user;

        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if ("此处输入开始时间".equals(textField1.getText())) {
                    textField1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(textField1.getText())) {
                    textField1.setText("此处输入开始时间");
                }
            }
        });
        textField1.setText("此处输入开始时间");

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
                return false;
            }
        };

        table1.getTableHeader().setReorderingAllowed(false);
        table1.setModel(defaultModel);
        /*{"id ", "电影id ", "电影名", "开始时间", "结束时间", "大厅id",
            "大厅内部编号", "大厅名","大厅所属影院编号", "大厅所属影院名称", "剩余票数", "票价"};*/

        for (FullSession session : fullSessions) {

            defaultModel.addRow(new Object[]{session.getId()
                    , session.getmName(), session.getsTime(), session.geteTime()
                    ,  session.getInsId(), session.gethName()
                    ,  session.getcName(), session.getCount()
                    , session.getPrice()});
        }
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date sTime = format.format(textField1.getText());
                    Date eTime = format.format(textField2.getText());
                    SessionService sessionService = new SessionServiceImpl();
                    List<FullSession> sessions=sessionService.findFullSessionByStime(sTime,eTime);
                    defaultModel.setDataVector((Object[][]) null, name);
                    for (FullSession session : sessions) {
                        if (fullSessions.contains(session)) {
                            defaultModel.addRow(new Object[]{session.getId(), session.getmId()
                                    , session.getmName(), session.getsTime(), session.geteTime()
                                    , session.gethId(), session.getInsId(), session.gethName()
                                    , session.getcId(), session.getcName(), session.getCount()
                                    , session.getPrice()});
                        }
                    }
                    Hidden.hiddenCell(table1,0);
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(panel1, "时间输入错误","错误",JOptionPane.WARNING_MESSAGE);
                    textField1.setText("");
                    return;
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }

            }


        });
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicketService ticketService = new TicketServiceImpl();
                int row = table1.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(panel1, "请选中一条记录","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    int sId = (Integer) table1.getValueAt(row, 0);
                    List<Integer> seats = ticketService.getSeats(sId);
                    Integer[] seat = seats.toArray(new Integer[seats.size()]);
                    Integer s = (Integer) JOptionPane.showInputDialog(null, "请选择你的座位号", "选座", JOptionPane.QUESTION_MESSAGE, null, seat, seat[0]);
                    if (s==null){
                        return;
                    }
                    ticketService.buyTicket(user.getId(),sId,s);

                    table1.setValueAt((Integer) table1.getValueAt(row, 7)-1,row,7);
                    user.setMoney(user.getMoney()-(Integer) table1.getValueAt(row, 8));
                    JOptionPane.showMessageDialog(panel1, "购票成功\r\n影院为"+table1.getValueAt(row,6)+
                            "付款金额为"+table1.getValueAt(row,8)+"请保留好此收据", "成功", JOptionPane.PLAIN_MESSAGE);
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        Hidden.hiddenCell(table1,0);
    }

    public  void getSession() {
        JFrame frame = new JFrame("场次");
        frame.setContentPane(new SessionFrame(user,fullSessions).panel1);
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
    }



}
