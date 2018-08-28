package com.frisk.dao;

import com.frisk.entity.Ticket;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 14:57
 */
public interface TicketDao {
    /**
     * 添加电影票记录
     * @param ticket 电影票信息
     */
    public void insert(Ticket ticket) throws SQLException;

    /**
     * 删除一条电影票记录
     * @param id 删除的信息的编号
     */
    public void delete(int id) throws SQLException;

    /**
     * 修改电影票记录
     * @param ticket 电影票修改后该有的样子,id不可更改
     */
    public void alter(Ticket ticket) throws SQLException;

    /**
     * 查询电影票信息
     * @param id 电影票id
     * @return 查找到的电影票
     */
    public Ticket query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;


    /**
     * 查询电影票信息
     * @param id 用户id
     * @return 查找到的电影票
     */
    public List<Ticket> queryByUid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 查询电影票信息
     * @param id 场次id
     * @return 查找到的电影票
     */
    public List<Ticket> queryBySid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 查询所有已售出的电影票的信息
     * @return 电影票
     */
    public List<Ticket> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

}
