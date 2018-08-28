package com.frisk.service.impl;

import com.frisk.MovieException;
import com.frisk.dao.MovieDao;
import com.frisk.dao.impl.MovieDaoImpl;
import com.frisk.entity.Movie;
import com.frisk.service.MovieService;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 20:01
 */
public class MovieServiceImpl implements MovieService {
    MovieDao movieDao = new MovieDaoImpl();

    /**
     * @return
     */
    @Override
    public List<Movie> showMovies() {
        List<Movie> query = null;
        try {
            query = movieDao.query();
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
     * @param id 电影的id
     * @return
     */
    @Override
    public Movie findMovieById(int id) throws MovieException {
        Movie movie = null;
        try {
            movie = movieDao.query(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (movie != null) {
            return movie;
        } else {
            throw new MovieException("未找到该电影");
        }

    }

    @Override
    public List<Movie> findMovieByName(String name) throws MovieException {
        List<Movie> query = null;
        try {
            query = movieDao.query(name);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (query.size()>0) {
            return query;
        }else {
            throw new MovieException("未找到该电影");
        }
    }

    /**
     * @param movie 需要添加的电影
     * @throws MovieException
     */
    @Override
    public void addMovie(Movie movie) throws MovieException {

        try {
            List<Movie> query = movieDao.query(movie.getName());
            for (int i = 0; i < query.size(); i++) {
                if (query.get(i).getName().equals(movie.getName()) &&
                        query.get(i).getMsg().equals(movie.getMsg())) {
                    throw new MovieException("该电影已存在");
                }
            }
            movieDao.insert(movie);
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
     * @param id 需要删除的电影的id
     */
    @Override
    public void deleteMovieById(int id) {
        try {
            movieDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param movie 修改电影的信息
     */
    @Override
    public void alterMovieById(Movie movie) {
        try {
            movieDao.alter(movie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
