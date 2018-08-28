package com.frisk.service.test;

import com.frisk.MovieException;
import com.frisk.entity.Movie;
import com.frisk.service.MovieService;
import com.frisk.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 20:09
 */
class MovieServiceTest {

    MovieService movieService = new MovieServiceImpl();
    @Test
    void showMovies() {
        List<Movie> movies = movieService.showMovies();
        for (Movie m:movies){
            System.out.println(m);
        }
    }

    @Test
    void findMovieById() {
        Movie movie = null;
        try {
            movie = movieService.findMovieById(7);
        } catch (MovieException e) {
            e.printStackTrace();
        }
        System.out.println(movie);

    }

    @Test
    void findMovieByName() {
        List<Movie> movies = null;
        try {
            movies = movieService.findMovieByName("f");
        } catch (MovieException e) {
            e.printStackTrace();
        }
        for (Movie m:movies){
            System.out.println(m);
        }
    }


    @Test
    void addMovie() {
        try {
            movieService.addMovie(new Movie("乱世佳人2" ,"乱世佳人》是根据玛格丽特·米切尔小说《飘》改编的爱情电影。" +
                    "由维克多·弗莱明 、乔治·库克、山姆·伍德导演，费雯·丽、克拉克·盖博等主演，于1940年1月17日在美国上映。" +
                    "影片以美国南北战争为背景，讲述了主人公斯嘉丽与白瑞德之间一段跌宕起伏的爱情故事。" +
                    "1940年，该在片奥斯卡金像奖颁奖典礼上获得了在包括最佳影片、最佳导演、最佳女主角在内的十个奖项。" +
                    "1998年，该片在美国电影协会评选的20世纪最伟大100部电影中位列第四。"));
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteMovieById() {
        movieService.deleteMovieById(1);
    }

    @Test
    void alterMovieById() {
        movieService.alterMovieById(new Movie(9,"乱世佳人","乱世佳人》是根据玛格丽特·米切尔小说《飘》改编的爱情电影。\n" +
                "由维克多·弗莱明 、乔治·库克、山姆·伍德导演，费雯·丽、克拉克·盖博等主演，于1940年1月17日在美国上映。\n" +
                "影片以美国南北战争为背景，讲述了主人公斯嘉丽与白瑞德之间一段跌宕起伏的爱情故事。\n" +
                "1940年，该在片奥斯卡金像奖颁奖典礼上获得了在包括最佳影片、最佳导演、最佳女主角在内的十个奖项。\n" +
                "1998年，该片在美国电影协会评选的20世纪最伟大100部电影中位列第四。\n"));
    }
}