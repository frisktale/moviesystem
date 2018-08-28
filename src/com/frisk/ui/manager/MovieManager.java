package com.frisk.ui.manager;

import com.frisk.MovieException;
import com.frisk.entity.Movie;
import com.frisk.entity.User;
import com.frisk.service.MovieService;
import com.frisk.service.impl.MovieServiceImpl;

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
 * Date: 2018/7/30
 * Time: 19:41
 */
public class MovieManager {
    private JPanel panel1;
    private JTable table1;
    private JTextField textField1;
    private JButton find;
    private JButton alter;
    private JButton delete;
    private JButton add;
    private JScrollPane js;
    private JPanel panel;

    MovieService movieService = new MovieServiceImpl();

    public MovieManager(){

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

        String[] name = {"id ", "电影名 ", "电影信息"};
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

        List<Movie> movies = movieService.showMovies();
        for (Movie movie : movies) {
            defaultModel.addRow(new Object[]{movie.getId(),movie.getName(),
                    (movie.getMsg())});
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
                String msg = null;
                try {
                    name = table1.getValueAt(count, 1).toString();
                    msg = table1.getValueAt(count, 2).toString();
                    if ("".equals(name) || "".equals(msg)){
                        throw new NullPointerException();
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "电影名和简介不能为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    movieService.addMovie(new Movie(id,name,msg));
                    defaultModel.setRowCount(0);
                    List<Movie> movies = movieService.showMovies();
                    for (Movie movie : movies) {
                        defaultModel.addRow(new Object[]{movie.getId(),movie.getName(),
                                (movie.getMsg())});
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
                int i = JOptionPane.showConfirmDialog(panel1, "是否删除编号为" + id + "的电影?", "删除提示",
                        JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    movieService.deleteMovieById(id);
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

                String name = null;
                String msg = null;
                try {
                    name = table1.getValueAt(count, 1).toString();
                    msg = table1.getValueAt(count, 2).toString();
                    if ("".equals(name) || "".equals(msg)){
                        throw new NullPointerException();
                    }
                } catch (NullPointerException e1) {
                    JOptionPane.showMessageDialog(panel1, "电影名和简介不能为空","错误",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                movieService.alterMovieById(new Movie(id,name,msg));
            }
        });
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Movie> movies = new ArrayList<>();
                Object[] fruits = {"查询全部","按id查询","按名字查询"};
                String str = "";
                str = (String) JOptionPane.showInputDialog(null, "选择查询方式",
                        "查询", JOptionPane.QUESTION_MESSAGE, null, fruits, fruits[0]);
                if (str == null){
                    return;
                }
                if ("查询全部".equals(str)){
                    movies = movieService.showMovies();
                }else if ("按id查询".equals(str)){
                    try {
                        int id = Integer.parseInt(textField1.getText());
                        Movie movie = movieService.findMovieById(id);
                        movies.clear();
                        movies.add(movie);
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
                        movies = movieService.findMovieByName(name);
                    } catch (MovieException e1) {
                        JOptionPane.showMessageDialog(panel1, e1.getMessage(),"错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                defaultModel.setDataVector((Object[][]) null,name);
                for (Movie movie : movies) {
                    defaultModel.addRow(new Object[]{movie.getId(), movie.getName(), movie.getMsg()});
                }
                defaultModel.addRow(new Vector());
                textField1.setText("输入查询关键字");
            }
        });

    }


    public JPanel getMovieManager(){
        return this.panel1;
    }
}
