package com.frisk.service.test;

import com.frisk.MovieException;
import com.frisk.entity.Comment;
import com.frisk.service.CommentService;
import com.frisk.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: frisk
 * Date: 2018/7/30
 * Time: 11:28
 */
class CommentServiceTest {

    CommentService commentService = new CommentServiceImpl();

    @Test
    void addComment() {
        try {
            commentService.addComment(new Comment(7, 7, "test4"));
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteComment() {
        commentService.deleteComment(4);
    }

    @Test
    void alterComment() {
        try {
            commentService.alterComment(new Comment(2,5,7,""));
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findCommentById() {
        try {
            Comment comment = commentService.findCommentById(2);
            System.out.println(comment);
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void query() {
        List<Comment> query = commentService.query();
        for (Comment comment : query) {
            System.out.println(comment);
        }
    }

    @Test
    void findCommentByUid() {
        try {
            for (Comment comment : commentService.findCommentByUid(5)) {
                System.out.println(comment);
            }
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findCommentByMid() {
        try {
            for (Comment comment : commentService.findCommentByMid(8)) {
                System.out.println(comment);
            }
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void writeComment() {
        try {
            commentService.writeComment(5,7,"好看");
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findCommentByUidAndMid() {
        System.out.println(commentService.findCommentByUidAndMid(5,7));
    }
}