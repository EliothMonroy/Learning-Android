package com.example.platzigram.model;

public class Picture {

    private String picture;
    private String userName;
    private String time;
    private String likes_number = "0";

    public Picture(String picture, String userName, String time, String likes_number) {
        this.picture = picture;
        this.userName = userName;
        this.time = time;
        this.likes_number = likes_number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikes_number() {
        return likes_number;
    }

    public void setLikes_number(String likes_number) {
        this.likes_number = likes_number;
    }

}
