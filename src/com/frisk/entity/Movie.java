package com.frisk.entity;

/**
 * User: frisk
 * Date: 2018/7/25
 * Time: 18:52
 */
public class Movie {
    private int id;
    private String name;
    private String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Movie(int id, String name, String msg) {

        this.id = id;
        this.name = name;
        this.msg = msg;
    }

    public Movie(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    public Movie(int id) {
        this.id = id;
    }

    public Movie() {

    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
