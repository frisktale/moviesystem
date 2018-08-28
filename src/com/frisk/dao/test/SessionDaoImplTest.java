package com.frisk.dao.test;

import com.frisk.MovieException;
import com.frisk.dao.SessionDao;
import com.frisk.dao.impl.SessionDaoImpl;
import com.frisk.entity.Session;
import com.frisk.utils.DateFormatByString;
import com.frisk.utils.impl.DateFormatByStringImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 11:01
 */
class SessionDaoImplTest {

    SessionDao sessionDao = new SessionDaoImpl();
    Session session;

    {
        try {
            session = new Session(1,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-3-19 12:00:00"),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-3-19 13:30:00"),1,1,1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void insert() {
        try {
            sessionDao.insert(session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        try {
            sessionDao.delete(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void alter() {
        session.setId(1);
        session.setCount(5);
        try {
            sessionDao.alter(session);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    void query() {
        List<Session> query = null;
        try {
            query = sessionDao.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (Session session:query){
            System.out.println(session);
        }
    }

    @Test
    void queryById() {
        try {
            Session session = sessionDao.queryById(5);
            System.out.println(session);
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
        List<Session> query = null;
        try {
            query = sessionDao.queryByMid(7);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (Session session:query){
            System.out.println(session);
        }
    }

    @Test
    void queryByHid() {
        List<Session> query = null;
        try {
            query = sessionDao.queryByHid(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (Session session:query){
            System.out.println(session);
        }
    }

    @Test
    void queryByStime(){
        DateFormatByString format = new DateFormatByStringImpl();
        try {
            List<Session> query = sessionDao.queryByStime(
                    format.format("2018-3-19 13:00:00"), format.format("2018-3-19 13:00:00"));
            for (Session session:query){
                System.out.println(session);
            }
        } catch (MovieException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}