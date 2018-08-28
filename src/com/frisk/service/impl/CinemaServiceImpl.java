package com.frisk.service.impl;

import com.frisk.MovieException;
import com.frisk.dao.CinemaDao;
import com.frisk.dao.impl.CinemaDaoImpl;
import com.frisk.entity.Cinema;
import com.frisk.service.CinemaService;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 22:15
 */
public class CinemaServiceImpl implements CinemaService {

    CinemaDao cinemaDao = new CinemaDaoImpl();

    /**
     * @param cinema 需要添加的电影院
     * @throws MovieException
     */
    @Override
    public void addCinema(Cinema cinema) throws MovieException {
        try {

            List<Cinema> query = cinemaDao.query(cinema.getName());
            for (Cinema c : query) {
                if (c.getName().equals(cinema.getName()) && c.getAddress().equals(cinema.getAddress())) {
                    throw new MovieException("此电影院已存在");
                }
            }
            cinemaDao.insert(cinema);
        } catch (SQLException e) {
            if (e.getMessage().contains("PRIMARY")) {
                throw new MovieException("该id已存在");
            }
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id 需要删除的电影院的id
     */
    @Override
    public void deleteCinemaById(int id) {
        try {
            cinemaDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param cinema 需要修改的电影院的名称和地址,以及定位的id
     */
    @Override
    public void alterCinemaById(Cinema cinema) {
        try {
            cinemaDao.alter(cinema);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    @Override
    public List<Cinema> showCinemas() {
        List<Cinema> query = null;
        try {
            query = cinemaDao.query();
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

    /**
     * @param id 需要查找的电影院的集合
     * @return
     */
    @Override
    public Cinema findCinemaById(int id) throws MovieException {
        Cinema cinema = null;

        try {
            cinema = cinemaDao.query(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (cinema != null) {
            return cinema;
        } else {
            throw new MovieException("未查到该影院");
        }
    }

    /**
     * @param name 电影院名字
     * @return
     */
    @Override
    public List<Cinema> findCinemaByName(String name) throws MovieException {
        List<Cinema> query = null;
        try {
            query = cinemaDao.query(name);
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
            throw new MovieException("未查到该影院");
        }
    }
}
