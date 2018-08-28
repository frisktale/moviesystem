package com.frisk.dao.test;

import com.frisk.MovieException;
import com.frisk.dao.SessionDao;
import com.frisk.dao.impl.SessionDaoImpl;
import com.frisk.entity.FullSession;
import com.frisk.entity.Session;
import com.frisk.utils.DateFormatByString;
import com.frisk.utils.impl.DateFormatByStringImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: frisk
 * Date: 2018/7/31
 * Time: 13:23
 */
class SessionDaoTest {

    SessionDao sessionDao = new SessionDaoImpl();
    @Test
    void queryFullSession() {
        try {
            List<FullSession> fullSessions = sessionDao.queryFullSession();
            for (FullSession fullSession : fullSessions) {
                System.out.println(fullSession);
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
    void findFullSessionByMid() {
        try {
            List<FullSession> fullSessionByMid = sessionDao.findFullSessionByMid(8);
            for (FullSession fullSession : fullSessionByMid) {
                System.out.println(fullSession);
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
    void findFullSessionByHid() {
        try {
            List<FullSession> fullSessionByHid = sessionDao.findFullSessionByHid(1);
            for (FullSession fullSession : fullSessionByHid) {
                System.out.println(fullSession);
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
    void findFullSessionByStime() {
        DateFormatByString format = new DateFormatByStringImpl();
        try {
            List<FullSession> query = sessionDao.findFullSessionByStime(
                    format.format("2018-3-19 13:00:00"), format.format("2018-3-19 14:00:00"));
            for (FullSession fullSession : query) {
                System.out.println(fullSession);
            }
        } catch (MovieException e) {
            e.printStackTrace();
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
    void findFullSessionById() {
        try {
            FullSession fullSessionById = sessionDao.findFullSessionById(5);
            System.out.println(fullSessionById);
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