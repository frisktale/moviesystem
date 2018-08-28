package com.frisk.service.test;

import com.frisk.MovieException;
import com.frisk.entity.Hall;
import com.frisk.service.CinemaService;
import com.frisk.service.HallService;
import com.frisk.service.impl.CinemaServiceImpl;
import com.frisk.service.impl.HallServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 13:40
 */
class HallServiceTest {

    HallService hallService = new HallServiceImpl();
    Hall hall = new Hall(8,3,10,3,"玫瑰");
    @Test
    void addHall() {

        try {
            hallService.addHall(hall);
        } catch (MovieException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void deleteHall() {
        hallService.deleteHall(10);
    }

    @Test
    void alterHall() {
        hall.setCount(30);
        try {
            hallService.alterHall(hall);
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findHallById() {
        Hall hall = null;
        try {
            hall = hallService.findHallById(8);
        } catch (MovieException e) {
            e.printStackTrace();
        }
        System.out.println(hall);
    }

    @Test
    void findHallByCinema() {
        CinemaService cinemaService = new CinemaServiceImpl();
        List<Hall> query = null;
        try {
            query = hallService.findHallByCinema(cinemaService.findCinemaById(1).getId());
        } catch (MovieException e) {
            e.printStackTrace();
        }
        for (Hall h:query){
            System.out.println(h);
        }
    }

    @Test
    void showHall() {
        List<Hall> query = hallService.showHall();
        for (Hall h:query){
            System.out.println(h);
        }
    }
}