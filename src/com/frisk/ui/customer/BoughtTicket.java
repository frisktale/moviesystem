package com.frisk.ui.customer;

import com.frisk.MovieException;
import com.frisk.entity.FullSession;
import com.frisk.entity.Ticket;
import com.frisk.entity.User;
import com.frisk.service.CommentService;
import com.frisk.service.SessionService;
import com.frisk.service.TicketService;
import com.frisk.service.impl.CommentServiceImpl;
import com.frisk.service.impl.SessionServiceImpl;
import com.frisk.service.impl.TicketServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;


/**
 * User: frisk
 * Date: 2018/8/1
 * Time: 19:53
 */
public class BoughtTicket {
    private JPanel panel1;
    private JTable table1;
    private JButton comment;
    User user;

    TicketService ticketService = new TicketServiceImpl();
    SessionService sessionService = new SessionServiceImpl();

    DefaultTableModel defaultModel;
    String[] name = {"电影id", "电影名", "开始时间", "结束时间",
            "大厅内部编号", "大厅名", "大厅所属影院名称", "座位号"};

    public BoughtTicket(User user) {
//        this.user = user;
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        defaultModel = new DefaultTableModel(null, name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table1.getTableHeader().setReorderingAllowed(false);
        table1.setModel(defaultModel);
        List<Ticket> tickets = null;
        try {
            tickets = ticketService.findTicketByUid(user.getId());
        } catch (MovieException e) {
            JOptionPane.showMessageDialog(panel1, e.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (Ticket ticket : tickets) {

            try {
                if (ticket.getsId() == null) {
                    defaultModel.addRow(new Object[]{"", "该场次由于", "不可抗因素", "无法播放",
                            "请及时联系", "管理员退票", "电影票id为", ticket.getId()});
                } else {
                    FullSession session = sessionService.findFullSessionById(ticket.getsId());
                    if (session.gethId() == null) {
                        defaultModel.addRow(new Object[]{"", "该场次由于", "不可抗因素", "无法播放",
                                "请及时联系", "管理员退票", "电影票id为", ticket.getId()});
                    }
                    defaultModel.addRow(new Object[]{session.getmId(), session.getmName(), session.getsTime(),
                            session.geteTime(), session.getInsId(), session.gethName()
                            , session.getcName(), ticket.getSeat()});
                }
            } catch (MovieException e) {
                break;
            }

            Hidden.hiddenCell(table1, 0);

        }
        comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(panel1, "请选中一条记录", "错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int mId = 0;
                try {
                    mId = (int) table1.getValueAt(row, 0);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(panel1, "请正确选择记录", "错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String comment = JOptionPane.showInputDialog(panel1, "请输入您对电影的评价");

                if (comment != null) {
                    CommentService commentService = new CommentServiceImpl();
                    try {
                        commentService.writeComment(user.getId(), mId, comment);
                        JOptionPane.showMessageDialog(panel1, "评论成功", "成功", JOptionPane.PLAIN_MESSAGE);
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }


            }
        });
    }

    public JPanel getBoughtTicket() {
        return panel1;
    }


}
