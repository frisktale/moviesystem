package com.frisk.dao;

import com.frisk.entity.Comment;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/28
 * Time: 17:17
 */
public interface CommentDao {
    public void insertComment(Comment comment) throws SQLException;
    public void deleteComment(int id) throws SQLException;
    public void alterComment(Comment comment) throws SQLException;
    public Comment query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public List<Comment> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public List<Comment> queryByUid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public List<Comment> queryByMid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public Comment queryByMidAndUid(int mId,int uId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    }
