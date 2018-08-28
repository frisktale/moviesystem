package com.frisk.dao.test;

import com.frisk.dao.CinemaDao;
import com.frisk.dao.impl.CinemaDaoImpl;
import com.frisk.entity.Cinema;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;


/**
 * User: frisk
 * Date: 2018/7/25
 * Time: 19:40
 */
class CinemaDaoImplTest {

    CinemaDao cinemaDao = new CinemaDaoImpl();
    @Test
    void insert() {
        try {
            cinemaDao.insert(new Cinema("东方红","一中旁边"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        try {
            cinemaDao.delete(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void alter() {
        try {
            cinemaDao.alter(new Cinema(2,"东方红","沙子巷边"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @org.junit.jupiter.api.Test
    void query1() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Cinema> query = cinemaDao.query();
        for(Cinema c:query){
            System.out.println(c);
        }
    }



    @Test
    void query() {
        try {
            Cinema query = cinemaDao.query(2);
            System.out.println(query);
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
            List<Cinema> query = cinemaDao.query("东");
            for(Cinema c:query){
                System.out.println(c);
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
    void query3() {
    }

    @Test
    void query4() {
    }
}