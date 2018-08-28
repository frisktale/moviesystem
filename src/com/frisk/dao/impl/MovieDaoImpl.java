package com.frisk.dao.impl;

import com.frisk.dao.MovieDao;
import com.frisk.dao.db.DBUtils;
import com.frisk.entity.Movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/25
 * Time: 20:03
 */
public class MovieDaoImpl implements MovieDao {
    /**
     * 插入数据
     *
     * @param movie 需要增加的数据
     */
    @Override
    public void insert(Movie movie) throws SQLException {
            DBUtils.execute("insert into movie(id,name,msg) values(?,?,?)", movie.getId(), movie.getName(), movie.getMsg());

    }

    /**
     * 删除
     *
     * @param id 需要删除的数据的id
     */
    @Override
    public void delete(int id) throws SQLException {
        DBUtils.execute("delete from movie where id = ?;", id);
    }

    /**
     * 修改,id作定位用,不可改变
     *
     * @param movie 数据应该被改成啥样
     */
    @Override
    public void alter(Movie movie) throws SQLException {
        DBUtils.execute("update movie set name=?,msg=? where id=?", movie.getName(), movie.getMsg(), movie.getId());
    }

    @Override
    public Movie query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Movie> query = DBUtils.query(Movie.class, "select * from movie where id = ?", id);
        if (query.isEmpty()){
            return null;
        }else {
            return query.get(0);
        }

    }

    @Override
    public List<Movie> query(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Movie> query = DBUtils.query(Movie.class, "select * from movie where name like '%' ? '%' ", name);
        return query;
    }

    /**
     * 条件查找,对为赋值的属性进行忽略处理
     *
     * @param movie 查找条件
     * @return
     */
    /*@Override
    public List<Movie> findTicketById(Movie movie) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Object> objs = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from movie where ");
        if (movie.getId() != 0) {
            sql.append("id=? and ");
            objs.add(movie.getId());
        }
        if (movie.getName() != null) {
            sql.append("name like '%' ? '%' and ");
            objs.add(movie.getName());
        }
        if (movie.getMsg() != null) {
            sql.append("msg like '%' ? '%' and ");
            objs.add(movie.getMsg());
        }
        sql.delete(sql.length() - 5, sql.length());
        sql.append(";");
        Object[] objects = objs.toArray();

        List<Movie> movies = null;
        movies = DBUtils.findTicketById(Movie.class, sql.toString(), objects);

        return movies;
    }*/

    /**
     * 查询所有信息
     *
     * @return
     */
    @Override
    public List<Movie> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Movie> movies = null;
        String sql = "select * from movie";
            movies = DBUtils.query(Movie.class, sql);
        return movies;
    }

}
