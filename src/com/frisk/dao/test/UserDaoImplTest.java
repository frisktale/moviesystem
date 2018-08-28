package com.frisk.dao.test;

import com.frisk.dao.UserDao;
import com.frisk.dao.impl.UserDaoImpl;
import com.frisk.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 13:41
 */
class UserDaoImplTest {

    UserDao userDao = new UserDaoImpl();
    User user = new User("py", "123");

    @Test
    void insert() {
        try {
            userDao.insert(new User(1, "admin", "admin", 1, 0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        try {
            userDao.delete(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void alter() {
        user.setId(1);
        user.setStatus(1);
        user.setMoney(1000);
        try {
            userDao.alter(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void query() {
//        User user1 = new User("xxx",null);
//        user1.setStatus(-1);
        User user = null;
        try {
            user = userDao.query(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }

    @Test
    void query1() {
        List<User> query = null;
        try {
            query = userDao.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (User u : query) {
            System.out.println(u);
        }
    }
    @Test
    void queryByName(){
        List<User> query = null;
        try {
            query = userDao.query("xxx");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(query.size());
        for (User u : query) {
            System.out.println(u);
        }
    }
}