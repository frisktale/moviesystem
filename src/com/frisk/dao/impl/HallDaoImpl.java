package com.frisk.dao.impl;

import com.frisk.dao.HallDao;
import com.frisk.dao.db.DBUtils;
import com.frisk.entity.Cinema;
import com.frisk.entity.Hall;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 11:38
 */
public class HallDaoImpl implements HallDao {
    /**
     * 添加
     *
     * @param hall 需要增加的数据
     * @throws SQLException
     */
    @Override
    public void insert(Hall hall) throws SQLException {
        DBUtils.execute("insert into hall(id,cId,count,hId,name) values(?,?,?,?,?)",
                hall.getId(), hall.getcId(), hall.getCount(), hall.gethId(), hall.getName());
    }

    /**
     * 删除
     *
     * @param id 需要删除的数据的id
     * @throws SQLException
     */
    @Override
    public void delete(int id) throws SQLException {
        DBUtils.execute("delete from hall where id = ?", id);
    }

    /**
     * 修改
     *
     * @param hall 数据应该被改成啥样
     * @throws SQLException
     */
    @Override
    public void alter(Hall hall) throws SQLException {
        DBUtils.execute("update hall set cId=?,count=?,hId=? ,name=? where id=?",
                hall.getcId(), hall.getCount(), hall.gethId(), hall.getName(), hall.getId());

    }

    @Override
    public Hall query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Hall> query = DBUtils.query(Hall.class, "select * from hall where id = ?", id);
        if (query.isEmpty()) {
            return null;
        } else {
            return query.get(0);
        }
    }

    @Override
    public List<Hall> queryByCid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Hall> query = DBUtils.query(Hall.class, "select * from hall where cId = ?", id);
        return query;
    }

    /**
     * 查询
     * @param hall 查找条件
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    /*@Override
    public List<Hall> findTicketById(Hall hall) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Object> objs = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select * from hall where ");
        if (hall.getId() != 0) {
            sql.append("id=? and ");
            objs.add(hall.getId());
        }
        if (hall.getcId() != 0) {
            sql.append("cId=? and ");
            objs.add(hall.getcId());
        }
        if (hall.getCount() != 0) {
            sql.append("count=? and ");
            objs.add(hall.getCount());
        }
        if (hall.gethId() != 0) {
            sql.append("hId=? and ");
            objs.add(hall.gethId());
        }
        if (hall.getName() != null) {
            sql.append("name=? and ");
            objs.add(hall.getName());
        }
        sql.delete(sql.length() - 5, sql.length() - 1);
        sql.append(";");

        Object[] objects = objs.toArray();

        List<Hall> halls = null;
        halls = DBUtils.findTicketById(Hall.class, sql.toString(), objects);

        return halls;
    }*/

    /**
     * 查询所有
     *
     * @return
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public List<Hall> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from hall");
        List<Hall> halls = null;
        halls = DBUtils.query(Hall.class, sql.toString());
        return halls;
    }
}
