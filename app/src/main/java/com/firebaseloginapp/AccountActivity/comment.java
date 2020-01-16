package com.firebaseloginapp.AccountActivity;


public class comment {

    private String content,uid,uname;
    private int uimg;
    private Object timestamp;


    public comment() {
    }

    public comment(String content, String uid, int uimg, String uname) {
        this.content = content;
        this.uid = uid;
        this.uimg = uimg;
        this.uname = uname;
        //this.timestamp = ServerValue.TIMESTAMP;

    }

    public comment(String content, String uid, int uimg, String uname, Object timestamp) {
        this.content = content;
        this.uid = uid;
        this.uimg = uimg;
        this.uname = uname;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getUimg() {
        return uimg;
    }

    public void setUimg(int uimg) {
        this.uimg = uimg;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

}
