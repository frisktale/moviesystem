package com.frisk.entity;

import java.util.Date;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 14:57
 */
public class Ticket {
    private int id;//影票编号
    private int uId;//用户id
    private Integer sId;//场次id
    private Date time;//购票时间
    private int seat;//座位

    public Ticket(int id, int uId, int sId, Date time, int seat) {
        this.id = id;
        this.uId = uId;
        this.sId = sId;
        this.time = time;
        this.seat = seat;
    }

    public Ticket(int uId, int sId, Date time, int seat) {
        this.uId = uId;
        this.sId = sId;
        this.time = time;
        this.seat = seat;
    }

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", uId=" + uId +
                ", sId=" + sId +
                ", time=" + time +
                ", seat=" + seat +
                '}';
    }
}
