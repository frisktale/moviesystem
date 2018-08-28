package com.frisk.ui.customer;

import com.frisk.MovieException;
import com.frisk.entity.Comment;
import com.frisk.entity.Movie;
import com.frisk.entity.User;
import com.frisk.service.CommentService;
import com.frisk.service.MovieService;
import com.frisk.service.impl.CommentServiceImpl;
import com.frisk.service.impl.MovieServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: frisk
 * Date: 2018/8/2
 * Time: 10:11
 */
public class CustomerCommentFrame {
    private JPanel panel1;
    private JTable table1;
    private JButton alter;

    User user;
    Map<Integer,Movie> movieMap = new LinkedHashMap<>();
    public CustomerCommentFrame(User user) {
//        this.user = user;
        DefaultTableModel defaultModel;
        String[] name = { "电影id","电影名","评价"};

        MovieService movieService = new MovieServiceImpl();
        this.user = user;

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        defaultModel = new DefaultTableModel(null, name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 2){
                    return true;
                }
                return false;
            }
        };
        table1.setModel(defaultModel);
        List<Movie> movies = movieService.showMovies();
        for (Movie movie : movies) {
            movieMap.put(movie.getId(),movie);
        }
        CommentService commentService = new CommentServiceImpl();
        try {
            List<Comment> comments = commentService.findCommentByUid(user.getId());
            System.out.println(comments.size());
            for (Comment comment : comments) {
                defaultModel.addRow(new Object[]{comment.getmId(),
                        movieMap.get(comment.getmId()).getName(),comment.getMsg()});
            }

        } catch (MovieException e) {
            JOptionPane.showMessageDialog(panel1, e.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
            return;
        }

        alter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(panel1, "请确保表格不在编辑状态\r\n(即所选行均为深色背景)\n\r", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (i != 0) {
                    return;
                }

                int row = table1.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(panel1, "请选中一条记录","错误",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int mId = (int) table1.getValueAt(row,0);
                String msg = "";
                try {
                    msg = table1.getValueAt(row, 2).toString();
                } catch (NullPointerException e1) {
                }

                try {
                    commentService.writeComment(user.getId(),mId,msg);
                    JOptionPane.showMessageDialog(panel1, "修改成功", "成功", JOptionPane.PLAIN_MESSAGE);
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public JPanel getCCommentFrame() {
        return panel1;
    }
}
