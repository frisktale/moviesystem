package com.frisk.service;

import com.frisk.MovieException;
import com.frisk.entity.FullSession;
import com.frisk.entity.Session;

import java.util.Date;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 10:07
 */
public interface SessionService {
    /**
     * 添加场次
     * @param session 需要添加的场次
     */
    public void addSession(Session session) throws MovieException;

    /**
     * 删除场次
     * @param id 需要删除的场次的id
     */
    public void deleteSession(int id);

    /**
     * 修改场次信息
     * @param session
     */
    public void alterSession(Session session) throws MovieException;
    public List<Session> showSession();
    public Session findSessionById(int id) throws MovieException;
    public List<Session> findSessionByCinema(int id) throws MovieException;
    public List<Session> findSessionByMovie(int id) throws MovieException;
    public List<Session> findSessionByStime(Date begin, Date end) throws MovieException;

    public List<FullSession> showFullSession();
    public FullSession findFullSessionById(int id) throws MovieException;
    public List<FullSession> findFullSessionByCinema(int id) throws MovieException;
    public List<FullSession> findFullSessionByMovie(int id) throws MovieException;
    public List<FullSession> findFullSessionByStime(Date begin, Date end) throws MovieException;
}
