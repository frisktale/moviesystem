package com.frisk.service.test;

import com.frisk.MovieException;
import com.frisk.entity.User;
import com.frisk.service.UserService;
import com.frisk.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 19:34
 */
class UserServiceTest {

    UserService userService = new UserServiceImpl();
    @Test
    void login() {
        try {
            System.out.println(userService.login("admin","12345"));
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void register() {
        try {
            userService.register("py123","123");
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addUser() {
        try {
            userService.addUser(new User(0,"ad","admin",1,500));
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteUser() {
        userService.deleteUser(6);
    }

    @Test
    void alterUser() {
        userService.alterUser(new User(1,"admin","admin",1,4000));
    }

    @Test
    void findUserById() {
        try {
            System.out.println(userService.findUserById(1));
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findUserByName() {
        try {
            List<User> users = userService.findUserByName("py");
            for (User u:users){
                System.out.println(u);
            }
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void showUser() {
        List<User> users = userService.showUser();
        for (User u : users) {
            System.out.println(u);
        }
    }

}