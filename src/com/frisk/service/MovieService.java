package com.frisk.service;

import com.frisk.MovieException;
import com.frisk.entity.Movie;

import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 19:59
 */
public interface MovieService {
    /**
     * 显示所有电影
     * @return 所有电影的集合
     */
    public List<Movie> showMovies();

    /**
     * 根据id查找电影
     * @param id 电影的id
     * @return 电影的集合
     */
    public Movie findMovieById(int id) throws MovieException;

    /**
     * 根据电影名称模糊查找电影
     * @param name
     * @return
     */
    public List<Movie> findMovieByName(String name) throws MovieException;

    /**
     * 添加电影
     * @param movie 需要添加的电影
     * @throws MovieException 若电影名与已存在的电影相同则抛出电影已存在异常
     */
    public void addMovie(Movie movie) throws MovieException;

    /**
     * 删除电影
     * @param id 需要删除的电影的id
     */
    public void deleteMovieById(int id);

    /**
     * 修改电影
     * @param movie 修改电影的信息
     */
    public void alterMovieById(Movie movie);
}
