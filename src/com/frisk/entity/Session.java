package com.frisk.entity;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 9:33
 */
public class Session {
    private int id;//场次id
    private int mId;//电影id
    private Date sTime;//开始时间
    private Date eTime;//结束时间
    private Integer hId;//大厅id
    private int count=-1;//剩余票数
    private int price;//票价

    public Session() {
    }

    public Session(int mId, Date sTime, Date eTime, Integer hId, int count, int price) {
        this.mId = mId;
        this.sTime = sTime;
        this.eTime = eTime;
        this.hId = hId;
        this.count = count;
        this.price = price;
    }

    public Session(int id, int mId, Date sTime, Date eTime, Integer hId, int count, int price) {
        this.id = id;
        this.mId = mId;
        this.sTime = sTime;
        this.eTime = eTime;
        this.hId = hId;
        this.count = count;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {

        return "SessionDao{" +
                "id=" + id +
                ", mId=" + mId +
                ", sTime=" + sTime +
                ", eTime=" + eTime +
                ", hId=" + hId +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
