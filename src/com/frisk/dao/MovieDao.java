package com.frisk.dao;

import com.frisk.entity.Cinema;
import com.frisk.entity.Movie;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/25
 * Time: 19:02
 */
public interface MovieDao {
    /**
     * 增加
     * @param movie 需要增加的数据
     */
    public void insert(Movie movie) throws SQLException;

    /**
     * 删除
     * @param id 需要删除的数据的id
     */
    public void delete(int id) throws SQLException;

    /**
     * 修改(用id定位数据,所以id是不可修改的)
     * @param movie 数据应该被改成啥样
     */
    public void alter(Movie movie) throws SQLException;

    /**
     * 根据电影id精确查找电影
     * @param id 查找条件
     * @return 通过条件查找到的数据集合
     */
    public Movie query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 根据电影名称模糊查询电影
     * @param name 查找条件
     * @return 所有数据的集合
     */

    public List<Movie> query(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 查询所有数据
     * @return 所有数据的集合
     */
    public List<Movie> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

}
