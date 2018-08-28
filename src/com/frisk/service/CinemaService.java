package com.frisk.service;

import com.frisk.MovieException;
import com.frisk.entity.Cinema;

import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 20:28
 */
public interface CinemaService {
    /**
     * 添加电影院
     * @param cinema 需要添加的电影院
     * @throws MovieException 当电影院的地址和名称与已存在的电影院相同时抛出电影院已存在的错误
     */
    public void addCinema(Cinema cinema) throws MovieException;

    /**
     * 删除电影院
     * @param id 需要删除的电影院的id
     */
    public void deleteCinemaById(int id);

    /**
     * 修改电影院信息
     * @param cinema 需要修改的电影院的名称和地址,以及定位的id
     */
    public void alterCinemaById(Cinema cinema);

    /**
     * 显示所有电影院
     * @return 电影院数组
     */
    public List<Cinema> showCinemas();

    /**
     * 根据id查找电影院
     * @param id 需要查找的电影院的集合
     * @return 电影院
     */
    public Cinema findCinemaById(int id) throws MovieException;

    /**
     * 根据姓名查询电影院
     * @param name 电影院名字
     * @return 查询结果
     */
    public List<Cinema> findCinemaByName(String name) throws MovieException;
}
