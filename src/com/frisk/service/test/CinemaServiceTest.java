package com.frisk.service.test;

import com.frisk.MovieException;
import com.frisk.dao.impl.CinemaDaoImpl;
import com.frisk.entity.Cinema;
import com.frisk.service.CinemaService;
import com.frisk.service.impl.CinemaServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: frisk
 * Date: 2018/7/27
 * Time: 9:46
 */
class CinemaServiceTest {

    CinemaService cinemaService = new CinemaServiceImpl();
    @Test
    void addCinema() {
        try {
            cinemaService.addCinema(new Cinema(3,"东方","沙子巷边"));
        } catch (MovieException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void deleteCinemaById() {
        cinemaService.deleteCinemaById(4);
    }

    @Test
    void alterCinemaById() {
        cinemaService.alterCinemaById(new Cinema(3,"江南","白鸽楼附近"));
    }

    @Test
    void showCinemas() {
        List<Cinema> cinemas = cinemaService.showCinemas();
        for (Cinema c:cinemas){
            System.out.println(c);
        }
    }

    @Test
    void findCinemaById() {
        Cinema cinema = null;
        try {
            cinema = cinemaService.findCinemaById(1);
        } catch (MovieException e) {
            e.printStackTrace();
        }
        System.out.println(cinema);

    }

    @Test
    void findCinemaByName() {
        List<Cinema> cinemas = null;
        try {
            cinemas = cinemaService.findCinemaByName("东");
        } catch (MovieException e) {
            e.printStackTrace();
        }
        for (Cinema c:cinemas){
            System.out.println(c);
        }
    }
}