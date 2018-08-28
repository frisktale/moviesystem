package com.frisk.dao;


import com.frisk.MovieException;
import com.frisk.entity.Cinema;
import com.frisk.entity.FullSession;
import com.frisk.entity.Movie;
import com.frisk.entity.Session;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 9:24
 */
public interface SessionDao {
    /**
     * 增加
     * @param session 需要增加的数据
     */
    public void insert(Session session) throws SQLException;

    /**
     * 删除
     * @param id 需要删除的数据的id
     */
    public void delete(int id) throws SQLException;

    /**
     * 修改(用id定位数据,所以id是不可修改的)
     * @param session 数据应该被改成啥样
     */
    public void alter(Session session) throws SQLException;

    /**
     * 根据场次id精确查找
     * @param id 查找条件
     * @return 通过条件查找到的数据集合
     */
    public Session queryById(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 根据电影模糊查询场次
     * @param id 查找条件
     * @return 通过条件查找到的数据集合
     */
    public List<Session> queryByMid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 根据放映厅id模糊查询
     * @param id 查找条件
     * @return 通过条件查找到的数据集合
     */
    public List<Session> queryByHid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;


    public List<Session> queryByStime(Date begin,Date end) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    /**
     * 查询全部信息
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public List<Session> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;


    public List<FullSession> queryFullSession() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public List<FullSession> findFullSessionByMid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public FullSession findFullSessionById(int id) throws  SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public List<FullSession> findFullSessionByHid(int id) throws  SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    public List<FullSession> findFullSessionByStime(Date begin, Date end) throws  SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
