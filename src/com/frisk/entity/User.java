package com.frisk.entity;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 12:37
 */
public class User {
    private int id;//用户id
    private String name;//用户名
    private String password;//用户密码
    private int status;//标识是否为管理员.因为数据库中没有boolean类型,所以以int型存储
    private int money;//用户余额

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public User(int id, String name, String password, int status, int money) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.status = status;
        this.money = money;
    }

    public User(String name, String password, int status, int money) {
        this.name = name;
        this.password = password;
        this.status = status;
        this.money = money;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", status=" + (status<0?false:true) +
                ", money=" + money +
                '}';
    }
}
