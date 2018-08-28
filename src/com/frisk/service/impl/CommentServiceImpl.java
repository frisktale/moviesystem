package com.frisk.service.impl;

import com.frisk.MovieException;
import com.frisk.dao.CommentDao;
import com.frisk.dao.impl.CommentDaoImpl;
import com.frisk.entity.Comment;
import com.frisk.service.CommentService;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/30
 * Time: 11:01
 */
public class CommentServiceImpl implements CommentService {
    CommentDao commentDao = new CommentDaoImpl();
    @Override
    public void addComment(Comment comment) throws MovieException {
        Comment comment1 = null;
        try {
            comment1 = commentDao.queryByMidAndUid(comment.getmId(), comment.getuId());
            if (comment1!=null){
                comment.setId(comment1.getId());
                commentDao.alterComment(comment);
            }else {
                commentDao.insertComment(comment);
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("PRIMARY")){
                throw new MovieException("该id已存在");
            }
            if (e.getMessage().contains("a foreign key constraint fails")){
                throw new MovieException("输入的用户id或电影id有误");
            }
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteComment(int id) {
        try {
            commentDao.deleteComment(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterComment(Comment comment) throws MovieException {
        try {
            commentDao.alterComment(comment);
        } catch (SQLException e) {
            if (e.getMessage().contains("a foreign key constraint fails")) {
                throw new MovieException("输入的用户或电影编号不存在");
            }
            e.printStackTrace();
        }
    }

    @Override
    public Comment findCommentById(int id) throws MovieException {
        Comment comment = null;
        try {
            comment = commentDao.query(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (comment==null){
            throw new MovieException("未找到此评论");
        }else {
            return comment;
        }
    }

    @Override
    public List<Comment> query() {
        List<Comment> query = null;
        try {
            query = commentDao.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public List<Comment> findCommentByUid(int id) throws MovieException {
        List<Comment> query = null;
        try {
            query = commentDao.queryByUid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (query==null){
            throw new MovieException("未查到该影院");
        }
        if (query.size()>0) {
            return query;
        }else {
            throw new MovieException("未找到此评论");
        }
    }

    @Override
    public List<Comment> findCommentByMid(int id) throws MovieException {
        List<Comment> query = null;
        try {
            query = commentDao.queryByMid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (query.size()>0) {
            return query;
        }else {
            throw new MovieException("未找到此评论");
        }
    }

    @Override
    public Comment findCommentByUidAndMid(int uId, int mId) {
        Comment comment = null;
        try {
            comment = commentDao.queryByMidAndUid(mId, uId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return comment;
    }

    @Override
    public void writeComment(int uId, int mId, String msg) throws MovieException {
        addComment(new Comment(uId,mId,msg));
    }
}
