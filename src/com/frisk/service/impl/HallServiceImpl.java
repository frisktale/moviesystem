package com.frisk.service.impl;

import com.frisk.MovieException;
import com.frisk.dao.HallDao;
import com.frisk.dao.impl.HallDaoImpl;
import com.frisk.entity.Cinema;
import com.frisk.entity.Hall;
import com.frisk.entity.Movie;
import com.frisk.service.HallService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 13:14
 */
public class HallServiceImpl implements HallService {
    HallDao hallDao = new HallDaoImpl();

    @Override
    public void addHall(Hall hall) throws MovieException {

        try {
            List<Hall> query = hallDao.queryByCid(hall.getcId());
            for (Hall h : query) {
                if (h.gethId() == hall.gethId() && h.getcId() == hall.getcId()) {
                    throw new MovieException("此放映厅已存在");
                }
            }
            hallDao.insert(hall);
        } catch (SQLException e) {
            if (e.getMessage().contains("PRIMARY")) {
                throw new MovieException("该id已存在");
            }
            if (e.getMessage().contains("a foreign key constraint fails")) {
                throw new MovieException("输入的电影院编号不存在");
            }
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHall(int id) {
        try {
            hallDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterHall(Hall hall) throws MovieException {
        try {
            hallDao.alter(hall);
        } catch (SQLException e) {
            if (e.getMessage().contains("a foreign key constraint fails")) {
                throw new MovieException("输入的电影院编号不存在");
            }
            e.printStackTrace();
        }
    }

    @Override
    public Hall findHallById(int id) throws MovieException {
        Hall hall = null;
        try {
            hall = hallDao.query(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (hall != null) {
            return hall;
        } else {
            throw new MovieException("未找到该大厅");
        }

    }

    @Override
    public List<Hall> findHallByCinema(int id) throws MovieException {
        List<Hall> halls = new ArrayList<>();
        try {
            List<Hall> query = hallDao.queryByCid(id);
            halls.addAll(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (halls.size() > 0) {
            return halls;
        } else {
            throw new MovieException("未找到该大厅");
        }
    }

    @Override
    public List<Hall> showHall() {
        List<Hall> query = null;
        try {
            query = hallDao.query();
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
