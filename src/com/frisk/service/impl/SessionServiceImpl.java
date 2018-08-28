package com.frisk.service.impl;

import com.frisk.MovieException;
import com.frisk.dao.SessionDao;
import com.frisk.dao.impl.SessionDaoImpl;
import com.frisk.entity.*;
import com.frisk.service.HallService;
import com.frisk.service.SessionService;
import com.frisk.utils.DateFormatByString;
import com.frisk.utils.impl.DateFormatByStringImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 10:23
 */
public class SessionServiceImpl implements SessionService {

    SessionDao sessionDao = new SessionDaoImpl();
    DateFormatByString format = new DateFormatByStringImpl();

    @Override
    public void addSession(Session session) throws MovieException {
        try {
            HallService hallService = new HallServiceImpl();
            List<Session> query = sessionDao.queryByHid(session.gethId());
            for (Session s : query) {
                if (!(session.geteTime().before(format.format(s.getsTime().toString())) ||
                        format.format(s.geteTime().toString()).before(session.getsTime()))||(session.geteTime().equals(format.format(s.getsTime().toString())) ||
                        format.format(s.geteTime().toString()).equals(session.getsTime()))) {
                    throw new MovieException("时间冲突");
                }
            }
            if(hallService.findHallById(session.gethId()).getCount()<session.getCount()){
                throw new MovieException("售票数大于放映厅座位数");
            }
            sessionDao.insert(session);
        } catch (SQLException e) {
            if (e.getMessage().contains("PRIMARY")) {
                throw new MovieException("该id已存在");
            }
            if (e.getMessage().contains("a foreign key constraint fails")) {
                throw new MovieException("输入的电影或者放映厅编号不存在");
            }
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSession(int id) {
        try {
            sessionDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterSession(Session session) throws MovieException {
        try {
            sessionDao.alter(session);
        } catch (SQLException e) {
            if (e.getMessage().contains("a foreign key constraint fails")) {
                throw new MovieException("输入的电影或者放映厅编号不存在");
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Session> showSession() {
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
        return query;
    }

    @Override
    public Session findSessionById(int id) throws MovieException {
        Session session = null;
        try {
            session = sessionDao.queryById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (session!=null) {
            return session;
        }else {
            throw new MovieException("未找到该场次");
        }
    }

    @Override
    public List<Session> findSessionByCinema(int id) throws MovieException {
        HallService hallService = new HallServiceImpl();
        List<Hall> halls = hallService.findHallByCinema(id);
        List<Session> sessions = new ArrayList<>();
        for (Hall h : halls) {
            Session session = new Session();
            session.sethId(h.getId());
            try {
                List<Session> query = sessionDao.queryByHid(h.getId());
                sessions.addAll(query);
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
        if (sessions.size()>0) {
            return sessions;
        }else {
            throw new MovieException("未找到该场次");
        }
    }

    @Override
    public List<Session> findSessionByMovie(int id) throws MovieException {
        List<Session> sessions = new ArrayList<>();

        try {
            sessions = sessionDao.queryByMid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (sessions.size()>0) {
            return sessions;
        }else {
            throw new MovieException("未找到该场次,电影还没上映");
        }
    }

    @Override
    public List<Session> findSessionByStime(Date begin, Date end) throws MovieException {
        List<Session> sessions = null;
        try {
            sessions = sessionDao.queryByStime(begin, end);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (sessions.size()>0) {
            return sessions;
        }else {
            throw new MovieException("未找到该场次");
        }
    }

    @Override
    public List<FullSession> showFullSession() {
        List<FullSession> query = null;
        try {
            query = sessionDao.queryFullSession();
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
    public FullSession findFullSessionById(int id) throws MovieException {
        FullSession session = null;
        try {
            session = sessionDao.findFullSessionById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (session!=null) {
            return session;
        }else {
            throw new MovieException("未找到该场次");
        }
    }

    @Override
    public List<FullSession> findFullSessionByCinema(int id) throws MovieException {
        HallService hallService = new HallServiceImpl();
        List<Hall> halls = hallService.findHallByCinema(id);
        List<FullSession> sessions = new ArrayList<>();
        for (Hall h : halls) {
            Session session = new Session();
            session.sethId(h.getId());
            try {
                List<FullSession> query = sessionDao.findFullSessionByHid(h.getId());
                sessions.addAll(query);
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
        if (sessions.size()>0) {
            return sessions;
        }else {
            throw new MovieException("未找到该场次");
        }
    }

    @Override
    public List<FullSession> findFullSessionByMovie(int id) throws MovieException {
        List<FullSession> sessions = new ArrayList<>();

        try {
            sessions = sessionDao.findFullSessionByMid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (sessions.size()>0) {
            return sessions;
        }else {
            throw new MovieException("未找到该场次");
        }
    }

    @Override
    public List<FullSession> findFullSessionByStime(Date begin, Date end) throws MovieException {
        List<FullSession> sessions = null;
        try {
            sessions = sessionDao.findFullSessionByStime(begin, end);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (sessions.size()>0) {
            return sessions;
        }else {
            throw new MovieException("未找到该场次");
        }
    }
}
