package com.frisk.service;

import com.frisk.MovieException;
import com.frisk.entity.Cinema;
import com.frisk.entity.Hall;
import com.frisk.entity.Movie;

import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 11:07
 */
public interface HallService {
    public void addHall(Hall hall) throws MovieException;
    public void deleteHall(int id);
    public void alterHall(Hall hall) throws MovieException;
    public Hall findHallById(int id) throws MovieException;
    public List<Hall> findHallByCinema(int id) throws MovieException;
    public List<Hall> showHall();
}
