package com.frisk.dao.impl;

import com.frisk.dao.UserDao;
import com.frisk.dao.db.DBUtils;
import com.frisk.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 13:05
 */
public class UserDaoImpl implements UserDao {
    /**
     * 添加用户
     *
     * @param user 需要添加的用户
     */
    @Override
    public void insert(User user) throws SQLException {
            DBUtils.execute("insert into user(id,name,password,status,money) values(?,?,?,?,?)",
                    user.getId(), user.getName(), user.getPassword(), user.getStatus(),user.getMoney());
    }

    /**
     * 删除用户
     *
     * @param id 需要删除的用户的id
     */
    @Override
    public void delete(int id) throws SQLException {
            DBUtils.execute("delete from user where id = ?", id);
    }

    @Override
    public void alter(User user) throws SQLException {
            DBUtils.execute("update user set name=?,password=?,status=?,money=? where id=?",
                    user.getName(), user.getPassword(), user.getStatus(),user.getMoney(), user.getId());
    }

    @Override
    public User query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<User> query = DBUtils.query(User.class, "select * from user where id = ?", id);
        if (query.isEmpty()){
            return null;
        }else {
            return query.get(0);
        }
    }

    @Override
    public List<User> query(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<User> query = DBUtils.query(User.class, "select * from user where name like '%' ? '%' ", name);
        return query;
    }

    /*@Override
    public List<User> findTicketById(User user) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Object> objs = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from user where ");
        if (user.getId() != 0) {
            sql.append("id=? and ");
            objs.add(user.getId());
        }
        if (user.getName() != null) {
            sql.append("name = ? and ");
            objs.add(user.getName());
        }
        if (user.getPassword() != null) {
            sql.append("password = ? and ");
            objs.add(user.getPassword());
        }
        if (user.getStatus()!=0) {
            sql.append("status = ? and ");
            objs.add(user.getStatus());
        }
        sql.delete(sql.length() - 5, sql.length());
        sql.append(";");

        Object[] objects = objs.toArray();
        List<User> users = null;
            users = DBUtils.findTicketById(User.class, sql.toString(), objects);
        return users;
    }*/

    @Override
    public List<User> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<User> users = null;
        String sql = "select * from user";
            users = DBUtils.query(User.class, sql);
        return users;
    }
}
