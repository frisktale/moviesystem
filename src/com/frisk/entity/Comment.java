package com.frisk.entity;

/**
 * User: frisk
 * Date: 2018/7/26
 * Time: 15:57
 */
public class Comment {
    private int id;//评论编号
    private int uId;//用户id
    private int mId;//电影id
    private String msg;//评价内容

    public Comment(int uId, int mId, String msg) {
        this.uId = uId;
        this.mId = mId;
        this.msg = msg;
    }

    public Comment() {
    }

    public Comment(int id, int uId, int mId, String msg) {
        this.id = id;
        this.uId = uId;
        this.mId = mId;
        this.msg = msg;
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

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CustomerCommentFrame{" +
                "id=" + id +
                ", uId=" + uId +
                ", mId=" + mId +
                ", msg='" + msg + '\'' +
                '}';
    }
}
