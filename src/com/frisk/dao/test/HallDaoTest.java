package com.frisk.dao.test;

import com.frisk.dao.HallDao;
import com.frisk.dao.impl.HallDaoImpl;
import com.frisk.entity.Hall;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 12:47
 */
class HallDaoTest {

    HallDao hallDao = new HallDaoImpl();
    Hall hall = new Hall(1,30,4,"4");
    @Test
    void insert() {
//        System.out.println(hall);
        try {
            hallDao.insert(hall);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        try {
            hallDao.delete(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void alter() {
        hall.setId(6);
        hall.setCount(50);
        try {
            hallDao.alter(hall);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void query1() {
        try {
            List<Hall> query = hallDao.query();
            for (Hall h:query){
                System.out.println(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void query2() {
        try {
            Hall hall = hallDao.query(3);
            System.out.println(hall);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void queryByCid() {
        try {
            List<Hall> query = hallDao.queryByCid(3);
            for (Hall h:query){
                System.out.println(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}