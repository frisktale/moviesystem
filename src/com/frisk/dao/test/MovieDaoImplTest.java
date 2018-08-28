package com.frisk.dao.test;

import com.frisk.dao.MovieDao;
import com.frisk.dao.impl.MovieDaoImpl;
import com.frisk.entity.Movie;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/25
 * Time: 20:10
 */
class MovieDaoImplTest {

    MovieDao movieDao = new MovieDaoImpl();
    @Test
    void insert() {
        try {
            movieDao.insert(new Movie("fsnhf","tm"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        try {
            movieDao.delete(6);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void alter() {
        try {
            movieDao.alter(new Movie(8,"fateubw","tm"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void query() {
        Movie movie = null;
        try {
            movie = movieDao.query(1);
            System.out.println(movie);
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
    void query1() {
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
        for(Movie c:query){
            System.out.println(c);
        }
    }

    @Test
    void query3() {
        try {
            List<Movie> query = movieDao.query("乱世佳人");
            for(Movie c:query){
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


}