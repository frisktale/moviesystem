package com.frisk.dao.impl;


import com.frisk.dao.SessionDao;
import com.frisk.dao.db.DBUtils;

import com.frisk.entity.FullSession;

import com.frisk.entity.Session;

import java.sql.SQLException;

import java.util.Date;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 10:35
 */
public class SessionDaoImpl implements SessionDao {

    /**
     * 增加数据
     *
     * @param session 需要增加的数据
     */
    @Override
    public void insert(Session session) throws SQLException {
        DBUtils.execute("insert into session values(?,?,?,?,?,?,?)",
                session.getId(), session.getmId(), session.getsTime(), session.geteTime(),
                session.gethId(), session.getCount(), session.getPrice());
    }

    /**
     * 删除数据
     *
     * @param id 需要删除的数据的id
     */
    @Override
    public void delete(int id) throws SQLException {
        DBUtils.execute("delete from session where id = ?", id);
    }

    /**
     * 修改数据,
     *
     * @param session 数据应该被改成啥样
     */
    @Override
    public void alter(Session session) throws SQLException {
        DBUtils.execute("update session set mId=?,sTime=?,eTime=?,hId=?,count=?,price=? where id=?",
                session.getmId(), session.getsTime(), session.geteTime(), session.gethId(), session.getCount(), session.getPrice(), session.getId());

    }

    @Override
    public Session queryById(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Session> query = DBUtils.query(Session.class, "select * from session where id = ?", id);
        if (query.isEmpty()) {
            return null;
        } else {
            return query.get(0);
        }
    }

    @Override
    public List<Session> queryByMid(int id) throws SQLException,
            NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Session> query = DBUtils.query(Session.class, "select * from session where mId = ?", id);
        return query;
    }

    @Override
    public List<Session> queryByHid(int id) throws SQLException,
            NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Session> query = DBUtils.query(Session.class, "select * from session where hId = ?", id);
        return query;
    }

    @Override
    public List<Session> queryByStime(Date begin, Date end) throws SQLException,
            NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Session> query = null;
        query = DBUtils.query(Session.class, "select * from session where sTime between ? and ?", begin, end);
        return query;
    }


    /**
     * 条件查询,忽略未被赋值的属性
     * 此时传入的起始时间和终止时间代表着要查询的电影的起始时间的范围
     *
     * @param session 查找条件
     * @return
     *//*
    @Override
    public List<SessionFrame> findTicketById(SessionFrame session) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Object> objs = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from session where ");
        if (session.getId() != 0) {
            sql.append("id=? and ");
            objs.add(session.getId());
        }
        if (session.getmId() != 0) {
            sql.append("mId=? and ");
            objs.add(session.getmId());
        }
        if (session.getsTime() != null&&session.geteTime() != null) {
            sql.append("sTime between ? and ? and ");
            objs.add(session.getsTime());
            objs.add(session.geteTime());
        }
        if (session.gethId() != 0) {
            sql.append("hId=? and ");
            objs.add(session.gethId());
        }
        if (session.getCount() != -1) {
            sql.append("count=? and ");
            objs.add(session.getCount());
        }
        if (session.getPrice() != -1) {
            sql.append("price=? and ");
            objs.add(session.getPrice());
        }
        sql.delete(sql.length() - 5, sql.length());
        sql.append(";");

        Object[] objects = objs.toArray();

        List<SessionFrame> sessions = null;
        sessions = DBUtils.findTicketById(SessionFrame.class, sql.toString(), objects);
        return sessions;
    }*/

    /**
     * 查询所有信息
     *
     * @return
     */
    @Override
    public List<Session> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Session> sessions = null;
        sessions = DBUtils.query(Session.class, "select * from session");
        return sessions;
    }

    @Override
    public List<FullSession> queryFullSession() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<FullSession> query = DBUtils.query(FullSession.class, "SELECT s.id,mId ,m.name mName,sTime,eTime,s.hId, " +
                "h.hId insId,h.name hName,c.id cId,c.name cName,s.count,price" +
                " FROM session s, movie m,hall h,cinema c where s.mId=m.id and s.hId=h.id and h.cId = c.id;");
        return query;
    }

    @Override
    public List<FullSession> findFullSessionByMid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<FullSession> query = DBUtils.query(FullSession.class, "SELECT s.id,mId ,m.name mName,sTime,eTime,s.hId, " +
                "h.hId insId,h.name hName,c.id cId,c.name cName,s.count,price" +
                " FROM session s, movie m,hall h,cinema c where s.mId=m.id and s.hId=h.id and h.cId = c.id and s.mId = ?;", id);
        return query;
    }

    @Override
    public FullSession findFullSessionById(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<FullSession> query = DBUtils.query(FullSession.class, "SELECT s.id,mId ,m.name mName,sTime,eTime,s.hId, " +
                "h.hId insId,h.name hName,c.id cId,c.name cName,s.count,price" +
                " FROM session s, movie m,hall h,cinema c where s.mId=m.id and s.hId=h.id and h.cId = c.id and s.id = ?;", id);
        if (query.isEmpty()) {
            return null;
        } else {
            return query.get(0);
        }
    }

    @Override
    public List<FullSession> findFullSessionByHid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<FullSession> query = DBUtils.query(FullSession.class, "SELECT s.id,mId ,m.name mName,sTime,eTime,s.hId, " +
                "h.hId insId,h.name hName,c.id cId,c.name cName,s.count,price" +
                " FROM session s, movie m,hall h,cinema c where s.mId=m.id and s.hId=h.id and h.cId = c.id and s.hId = ?;", id);
        return query;
    }


    @Override
    public List<FullSession> findFullSessionByStime(Date begin, Date end) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<FullSession> query = DBUtils.query(FullSession.class, "SELECT s.id,mId ,m.name mName,sTime,eTime,s.hId, " +
                "h.hId insId,h.name hName,c.id cId,c.name cName,s.count,price" +
                " FROM session s, movie m,hall h,cinema c " +
                "where s.mId=m.id and s.hId=h.id and h.cId = c.id and sTime between ? and ? ;", begin, end);
        return query;
    }
}
