package com.frisk.dao.impl;

import com.frisk.dao.CinemaDao;
import com.frisk.dao.db.DBUtils;
import com.frisk.entity.Cinema;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/25
 * Time: 19:03
 */
public class CinemaDaoImpl implements CinemaDao {


    /**
     * 插入
     *
     * @param cinema
     */
    @Override
    public void insert(Cinema cinema) throws SQLException {
        DBUtils.execute("insert into cinema(id,name,address) values(?,?,?)", cinema.getId(), cinema.getName(), cinema.getAddress());

    }

    /**
     * 根据删除
     *
     * @param id 需要删除的数据的id
     */
    @Override
    public void delete(int id) throws SQLException {
        DBUtils.execute("delete from cinema where id = ?", id);
    }

    /**
     * 修改
     *
     * @param cinema 数据应该被改成啥样
     */
    @Override
    public void alter(Cinema cinema) throws SQLException {
        DBUtils.execute("update cinema set name=?,address=? where id=?",
                cinema.getName(), cinema.getAddress(), cinema.getId());

    }

    /**
     * 通过id查询电影院
     * @param id 电影院id
     * @return 一个电影院
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public Cinema query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Cinema> query = DBUtils.query(Cinema.class, "select * from cinema where id=?", id);
        if (query.isEmpty()) {
            return null;
        } else {
            return query.get(0);
        }
    }

    /**
     * 通过电影院名字模糊查询电影院
     * @param name 电影名称
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public List<Cinema> query(String name) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Cinema> query = DBUtils.query(Cinema.class, "select * from cinema where name like '%' ? '%' ", name);

        return query;

    }

    /**
     * 条件查询,对sql语句进行拼接,忽略掉未被赋值(即还是默认值)的数据
     * @param cinema 查找条件
     * @return
     */    /*@Override
    public List<Cinema> findTicketById(Cinema cinema) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Object> objs = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from cinema where ");
        if (cinema.getId()!=0){
            sql.append("id=? and ");
            objs.add(cinema.getId());
        }
        if (cinema.getName()!=null){
            sql.append("name like '%' ? '%' and ");
            objs.add(cinema.getName());
        }
        if (cinema.getAddress()!=null){
            sql.append("address like '%' ? '%' and ");
            objs.add(cinema.getAddress());
        }
        sql.delete(sql.length()-5,sql.length());
        sql.append(";");

        Object[] objects = objs.toArray();

        List<Cinema> cinemas = null;
            cinemas = DBUtils.findTicketById(Cinema.class, sql.toString(), objects);

        return cinemas;
    }*/

    /**
     * 查询所有数据
     *
     * @return
     */
    @Override
    public List<Cinema> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Cinema> cinemas = null;
        String sql = "select * from cinema";
        cinemas = DBUtils.query(Cinema.class, sql);
        return cinemas;
    }
}
