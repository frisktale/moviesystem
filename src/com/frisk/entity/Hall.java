package com.frisk.entity;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 16:08
 */
public class Hall {
    private int id;//放映厅编号
    private int cId;//所属影院id
    private int count;//座位数
    private int hId;//再所属影院中的编号
    private String name;//放映厅名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int gethId() {
        return hId;
    }

    public void sethId(int hId) {
        this.hId = hId;
    }

    public Hall() {
    }

    public Hall(int id, int cId, int count, int hId, String name) {
        this.id = id;
        this.cId = cId;
        this.count = count;
        this.name = name;
        this.hId = hId;
    }

    public Hall(int cId, int count, int hId, String name) {
        this.cId = cId;
        this.count = count;
        this.hId = hId;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", cId=" + cId +
                ", count=" + count +
                ", hId=" + hId +
                ", name='" + name + '\'' +
                '}';
    }
}
