package com.frisk.dao;

import com.frisk.entity.Cinema;
import com.frisk.entity.Hall;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 11:33
 */
public interface HallDao {
    /**
     * 增加
     * @param hall 需要增加的数据
     */
    public void insert(Hall hall) throws SQLException;

    /**
     * 删除
     * @param id 需要删除的数据的id
     */
    public void delete(int id) throws SQLException;

    /**
     * 修改(用id定位数据,所以id是不可修改的)
     * @param hall 数据应该被改成啥样
     */
    public void alter(Hall hall) throws SQLException;

    /**
     * 根据放映厅id精确查找
     * @param id 查找条件
     * @return 通过条件查找到的数据集合
     */
    public Hall query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 根据电影院的id模糊查找放映厅
     * @param id 查找条件
     * @return 通过条件查找到的数据集合
     */
    public List<Hall> queryByCid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     * 查询所有数据
     * @return 所有数据的集合
     */
    public List<Hall> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
