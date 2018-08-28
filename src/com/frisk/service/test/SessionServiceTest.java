package com.frisk.service.test;

import com.frisk.MovieException;
import com.frisk.entity.FullSession;
import com.frisk.entity.Session;
import com.frisk.service.SessionService;
import com.frisk.service.impl.SessionServiceImpl;
import com.frisk.utils.DateFormatByString;
import com.frisk.utils.impl.DateFormatByStringImpl;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 14:11
 */
class SessionServiceTest {

    SessionService sessionService = new SessionServiceImpl();
    Session session;

    {
        try {
            session = new Session(8,new DateFormatByStringImpl().format("2018-3-19 9:30:00"),
                        new DateFormatByStringImpl().format("2018-3-19 11:00:00"),1,1,1);
        } catch (MovieException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void addSession() {
        try {
            sessionService.addSession(session);
        } catch (MovieException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void deleteSession() {
        sessionService.deleteSession(6);
    }

    @Test
    void alterSession() {

        try {
            sessionService.alterSession(session);
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void showSession() {
        List<Session> sessions = sessionService.showSession();
        for (Session s:sessions){
            System.out.println(s);
        }
    }

    @Test
    void findSessionById() {
        try {
            Session sessionById = sessionService.findSessionById(5);
            System.out.println(sessionById);
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findSessionByCinema() {
        try {
            List<Session> sessionByCinema = sessionService.findSessionByCinema(1);
            for (Session session1 : sessionByCinema) {
                System.out.println(session1);
            }
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findSessionByMovie() {
        List<Session> sessionByMovie = null;
        try {
            sessionByMovie = sessionService.findSessionByMovie(8);
        } catch (MovieException e) {
            e.printStackTrace();
        }
        for (Session session1 : sessionByMovie) {
            System.out.println(session1);
        }
    }

    @Test
    void showFullSession() {
            List<FullSession> fullSessions = sessionService.showFullSession();
            for (FullSession fullSession : fullSessions) {
                System.out.println(fullSession);
            }
    }

    @Test
    void findFullSessionById() {
        try {
            FullSession fullSessionById = sessionService.findFullSessionById(14);
            System.out.println(fullSessionById);
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findFullSessionByCinema() {
        try {
            List<FullSession> fullSessionByCinema = sessionService.findFullSessionByCinema(1);
            for (FullSession fullSession : fullSessionByCinema) {
                System.out.println(fullSession);
            }
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findFullSessionByMovie() {
        try {
            List<FullSession> fullSessionByMovie = sessionService.findFullSessionByMovie(8);
            System.out.println(fullSessionByMovie);
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findFullSessionByStime() {
        DateFormatByString format = new DateFormatByStringImpl();
        try {
            List<FullSession> fullSessionByStime = sessionService.findFullSessionByStime(format.format("2018-3-19 13:00:00"), format.format("2018-3-19 14:00:00"));
            for (FullSession fullSession : fullSessionByStime) {
                System.out.println(fullSession);
            }
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findSessionByStime() {
        DateFormatByString format = new DateFormatByStringImpl();
        try {
            List<Session> sessionByStime = sessionService.findSessionByStime(format.format("2018-3-19 13:00:00"), format.format("2018-3-19 14:00:00"));
            for (Session session1 : sessionByStime) {
                System.out.println(session1);
            }
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }
}