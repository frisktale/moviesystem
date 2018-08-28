package com.frisk.dao.impl;

import com.frisk.dao.CommentDao;
import com.frisk.dao.db.DBUtils;
import com.frisk.entity.Cinema;
import com.frisk.entity.Comment;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/28
 * Time: 17:33
 */
public class CommentDaoImpl implements CommentDao {
    @Override
    public void insertComment(Comment comment) throws SQLException {
        DBUtils.execute("INSERT INTO comment (`id`, `uId`, `mId`, `msg`) VALUES (?, ?, ?, ?)"
                , comment.getId(),comment.getuId(),comment.getmId(),comment.getMsg());
    }

    @Override
    public void deleteComment(int id) throws SQLException {
        DBUtils.execute("delete from comment where id = ?",id);
    }

    @Override
    public void alterComment(Comment comment) throws SQLException {
        DBUtils.execute("update comment set uId=?,mId=?,msg=? where id=?",
                comment.getuId(),comment.getmId(),comment.getMsg(), comment.getId());
    }

    @Override
    public Comment query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Comment> query = DBUtils.query(Comment.class, "select * from comment where id=?", id);
        if (query.isEmpty()) {
            return null;
        }else {
            return query.get(0);
        }
    }

    @Override
    public List<Comment> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Comment> cinemas = null;
        String sql = "select * from comment";
        cinemas = DBUtils.query(Comment.class, sql);
        return cinemas;
    }

    @Override
    public List<Comment> queryByUid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Comment> query = DBUtils.query(Comment.class, "select * from comment where uId = ? ", id);
        return query;

    }

    @Override
    public List<Comment> queryByMid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Comment> query = DBUtils.query(Comment.class, "select * from comment where mId = ? ", id);
        return query;
    }

    @Override
    public Comment queryByMidAndUid(int mId,int uId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Comment> query = DBUtils.query(Comment.class, "select * from comment where mId = ? and uId = ? ", mId,uId);
        if (query.isEmpty()) {
            return null;
        }else {
            return query.get(0);
        }
    }
}

