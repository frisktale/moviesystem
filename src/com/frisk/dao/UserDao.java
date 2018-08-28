package com.frisk.dao;

import com.frisk.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 12:49
 */
public interface UserDao {
    /**
     * 添加用户
     * @param user 需要添加的用户
     */
    public void insert(User user) throws SQLException;

    /**
     * 删除用户
     * @param id 需要删除的用户的id
     */
    public void delete(int id) throws SQLException;

    /**
     * 修改用户信息,id不可修改
     * @param user 指定用户修改后的信息
     */
    public void alter(User user) throws SQLException;

    /**
     * 根据用户id精确查询
     * @param id 查询条件
     * @return 查询的结果
     */
    public User query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;


    /**
     * 根据用户名模糊查询
     * @param name 用户名
     * @return 查询的结果
     */
    public List<User> query(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;


    /**
     * 获取所有用户的信息
     * @return 所有用户的信息
     */
    public List<User> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;
}
