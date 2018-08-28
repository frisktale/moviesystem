package com.frisk.service;

import com.frisk.MovieException;
import com.frisk.entity.User;

import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 14:49
 */
public interface UserService {
    /**
     * 登陆
     * @param name 用户名
     * @param password 密码
     * @return 返回身份标识码,普通用户为-1,管理员为1
     * @throws MovieException 用户名或密码错误则抛出异常
     */
    public User login(String name, String password) throws MovieException;

    /**
     * 注册
     * @param name
     * @param password
     * @throws MovieException
     */
    public void register(String name,String password) throws MovieException;

    public void addUser(User user) throws MovieException;
    public void deleteUser(int id);
    public void alterUser(User user);
    public User findUserById(int id) throws MovieException;
    public List<User> findUserByName(String name) throws MovieException;
    public List<User> showUser();

}
