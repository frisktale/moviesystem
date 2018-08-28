package com.frisk.dao.test;

import com.frisk.dao.CommentDao;
import com.frisk.dao.impl.CommentDaoImpl;
import com.frisk.entity.Comment;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/29
 * Time: 9:35
 */
class CommentDaoTest {

    CommentDao commentDao = new CommentDaoImpl();
    Comment comment = new Comment(5,8,"test");
    @Test
    void addComment() {
        try {
            commentDao.insertComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteComment() {
        try {
            commentDao.deleteComment(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void alterComment() {
        comment.setId(2);
        comment.setMsg("test2");
        try {
            commentDao.alterComment(comment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void query() {
        try {
            System.out.println(commentDao.query(3));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void query1() {
        try {
            List<Comment> query = commentDao.query();
            for (Comment c:query){
                System.out.println(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void queryByUid() {
        try {
            List<Comment> query = commentDao.queryByUid(5);
            for (Comment c:query){
                System.out.println(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void queryByMid() {
        try {
            List<Comment> query = commentDao.queryByMid(8);
            for (Comment c:query){
                System.out.println(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}