package com.frisk.dao;

import com.frisk.entity.Cinema;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/25
 * Time: 18:34
 */
public interface CinemaDao {
    /**
     * 增加
     * @param cinema 需要增加的数据
     */
    public void insert(Cinema cinema) throws SQLException;

    /**
     * 删除
     * @param id 需要删除的数据的id
     */
    public void delete(int id) throws SQLException;

    /**
     * 修改(用id定位数据,所以id是不可修改的)
     * @param cinema 数据应该被改成啥样
     */
    public void alter(Cinema cinema) throws SQLException;

    /**
     * 根据电影院id精确查找
     * @param id 电影院id
     * @return 通过条件查找到的数据集合
     */
    public Cinema query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 根据电影院名称模糊查找
     * @param name 电影名称
     * @return 通过条件查找到的数据集合
     */
    public List<Cinema> query(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 查询所有数据
     * @return 所有数据的集合
     */
    public List<Cinema> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
