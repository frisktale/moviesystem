package com.frisk.service;

import com.frisk.MovieException;
import com.frisk.entity.Comment;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/30
 * Time: 10:44
 */
public interface CommentService {
    public void addComment(Comment comment) throws MovieException;
    public void deleteComment(int id);
    public void alterComment(Comment comment) throws MovieException;
    public Comment findCommentById(int id) throws MovieException;
    public List<Comment> query();
    public List<Comment> findCommentByUid(int id) throws MovieException;
    public List<Comment> findCommentByMid(int id) throws MovieException;

    public Comment findCommentByUidAndMid(int uId,int mId);

    public void writeComment(int uId,int mId,String msg) throws MovieException;
}
