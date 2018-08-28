package com.frisk.entity;

import java.util.Date;
import java.util.Objects;

/**
 * User: frisk
 * Date: 2018/7/31
 * Time: 13:01
 */
public class FullSession {
    private int id;
    private int mId;
    private String mName;
    private Date sTime;
    private Date eTime;
    private Integer hId;
    private int insId;
    private String hName;
    private int cId;
    private String cName;
    private int count;
    private int price;

    public FullSession() {
    }

    public FullSession(int id, int mId, String mName, Date sTime, Date eTime, Integer hId, int insId, String hName, int cId, String cName, int count, int price) {
        this.id = id;
        this.mId = mId;
        this.mName = mName;
        this.sTime = sTime;
        this.eTime = eTime;
        this.hId = hId;
        this.insId = insId;
        this.hName = hName;
        this.cId = cId;
        this.cName = cName;
        this.count = count;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public Date getsTime() {
        return sTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public Integer gethId() {
        return hId;
    }

    public int getInsId() {
        return insId;
    }

    public String gethName() {
        return hName;
    }

    public int getcId() {
        return cId;
    }

    public String getcName() {
        return cName;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }

    public void setInsId(int insId) {
        this.insId = insId;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FullSession{" +
                "id=" + id +
                ", mId=" + mId +
                ", mName='" + mName + '\'' +
                ", sTime=" + sTime +
                ", eTime=" + eTime +
                ", hId=" + hId +
                ", insId=" + insId +
                ", hName='" + hName + '\'' +
                ", cId=" + cId +
                ", cName='" + cName + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FullSession that = (FullSession) o;
        return id == that.id &&
                mId == that.mId &&
                hId.equals(that.hId) &&
                insId == that.insId &&
                cId == that.cId &&
                count == that.count &&
                price == that.price &&
                Objects.equals(mName, that.mName) &&
                Objects.equals(sTime, that.sTime) &&
                Objects.equals(eTime, that.eTime) &&
                Objects.equals(hName, that.hName) &&
                Objects.equals(cName, that.cName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mId, mName, sTime, eTime, hId, insId, hName, cId, cName, count, price);
    }
}
