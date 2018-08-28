package com.frisk.ui.customer.ticket;

import com.frisk.MovieException;
import com.frisk.entity.Comment;
import com.frisk.entity.FullSession;
import com.frisk.entity.Movie;
import com.frisk.entity.User;
import com.frisk.service.CommentService;
import com.frisk.service.MovieService;
import com.frisk.service.SessionService;
import com.frisk.service.impl.CommentServiceImpl;
import com.frisk.service.impl.MovieServiceImpl;
import com.frisk.service.impl.SessionServiceImpl;
import com.frisk.ui.customer.Hidden;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/8/1
 * Time: 12:35
 */
public class MovieList {
    private JPanel panel1;
    private JTable table1;
    private JButton choose;
    private JPanel panel2;
    private JTextField textField1;
    private JButton search;
    private JButton comment;
    private JButton allSession;
    private JButton message;
    User user;
    MovieService movieService = new MovieServiceImpl();

    public MovieList(User user) {
//        this.user = user;
        String[] name = {"id ", "电影名 ", "电影信息"};
        DefaultTableModel defaultModel = new DefaultTableModel(null, name) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.getTableHeader().setReorderingAllowed(false);
        table1.setModel(defaultModel);

        List<Movie> movies = movieService.showMovies();
        for (Movie movie : movies) {
            defaultModel.addRow(new Object[]{movie.getId(), movie.getName(),
                    (movie.getMsg())});
        }

        Hidden.hiddenCell(table1,0);

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = table1.getSelectedRow();
                int mId = 0;
                try {
                    mId = (int) table1.getValueAt(select, 0);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(panel1, "请选择一个电影", "错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                SessionService sessionService = new SessionServiceImpl();
                try {
                    List<FullSession> fullSessionByMovie = sessionService.findFullSessionByMovie(mId);
                    new SessionFrame(user,fullSessionByMovie).getSession();
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Movie> movies;
                String name = textField1.getText();
                try {
                    movies = movieService.findMovieByName(name);
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                defaultModel.setRowCount(0);
                for (Movie movie : movies) {
                    defaultModel.addRow(new Object[]{movie.getId(), movie.getName(), movie.getMsg()});
                }
            }
        });
        comment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = table1.getSelectedRow();
                int mId = 0;
                try {
                    mId = (int) table1.getValueAt(select, 0);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(panel1, "请选择一个电影", "错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                CommentService commentService = new CommentServiceImpl();
                try {
                    List<Comment> comments = commentService.findCommentByMid(mId);
                    new CommentFrame(comments).getCommentFrame();
                } catch (MovieException e1) {
                    JOptionPane.showMessageDialog(panel1, e1.getMessage(), "错误", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        allSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SessionService sessionService = new SessionServiceImpl();
                    List<FullSession> fullSessionByMovie = sessionService.showFullSession();
                    new SessionFrame(user,fullSessionByMovie).getSession();
            }
        });
        message.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = table1.getSelectedRow();
                String msg = (String) table1.getValueAt(select,2);
                new Msg(msg);
            }
        });
    }



    public JPanel getMovieList() {
        return panel1;
    }


}
