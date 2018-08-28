package com.frisk.service.impl;

import com.frisk.MovieException;
import com.frisk.dao.UserDao;
import com.frisk.dao.impl.UserDaoImpl;
import com.frisk.entity.User;
import com.frisk.service.UserService;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 17:10
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    /**
     * 登陆
     *
     * @param name     用户名
     * @param password 密码
     * @return 若登陆成功则返回权限代码, 1代表管理员,-1代表普通用户
     * @throws MovieException 抛出登陆失败的错误
     */
    @Override
    public User login(String name, String password) throws MovieException {
        List<User> query = findUserByName(name);
        for (User u : query) {
            if (u.getName().equals(name) && u.getPassword().equals(password)) {
                return query.get(0);
            }
        }
        throw new MovieException("登陆失败,用户名或密码错误");
    }

    /**
     * 注册
     *
     * @param name     注册用户名
     * @param password 注册密码
     * @throws MovieException 若用户名已被占用则抛出已被占用的异常
     */
    @Override
    public void register(String name, String password) throws MovieException {
        User user = new User(name, password);
//            普通用户注册时,设置权限为-1
        user.setStatus(-1);
        addUser(user);

    }

    @Override
    public void addUser(User user) throws MovieException {
        List<User> query = null;
        try {
            query = userDao.query(user.getName());
            for (User u : query) {
                if (u.getName().equals(user.getName())) {
                    throw new MovieException("用户名已存在");
                }
            }
            userDao.insert(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            if (e.getMessage().contains("PRIMARY")) {
                throw new MovieException("该id已存在");
            }
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            userDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterUser(User user) {
        try {
            userDao.alter(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserById(int id) throws MovieException {
        User user = null;
        try {
            user = userDao.query(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (user != null) {
            return user;
        } else {
            throw new MovieException("未找到该用户");
        }
    }

    @Override
    public List<User> findUserByName(String name) throws MovieException {
        List<User> query = null;
        try {
            query = userDao.query(name);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (query.size() > 0) {
            return query;
        } else {
            throw new MovieException("未找到该用户");
        }
    }

    @Override
    public List<User> showUser() {
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
        return query;
    }
}
