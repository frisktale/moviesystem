package com.frisk.ui.customer.ticket;

import com.frisk.entity.Comment;
import com.frisk.entity.User;
import com.frisk.service.UserService;
import com.frisk.service.impl.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: frisk
 * Date: 2018/8/1
 * Time: 14:59
 */
public class CommentFrame {
    private JPanel panel1;
    private JTable table1;

    List<Comment> comments;

    public CommentFrame(List<Comment> comments){
        this.comments = comments;
        String[] name = { "用户名 ",  "评论内容"};
        Map<Integer, User> userMap = new LinkedHashMap<>();
        UserService userService = new UserServiceImpl();
        List<User> users = userService.showUser();
        for (User user : users) {
            userMap.put(user.getId(),user);
        }
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

        for (Comment comment : comments) {
            defaultModel.addRow(new Object[]{ userMap.get(comment.getuId()).getName(), comment.getMsg()});
        }
    }

    public void getCommentFrame() {
        JFrame frame = new JFrame("CustomerCommentFrame");
        frame.setContentPane(new CommentFrame(comments).panel1);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }


}
