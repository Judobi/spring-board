package com.example.springboard.vo;

import java.time.LocalDateTime;

public class User {

    private int uid;
    private String userId;
    private String password;
    private String nickname;
    private LocalDateTime timeCreated;

    private LocalDateTime timeDeleted;
    private int accessId;

    public User(){}

    public User(int uid, String userId, String password, String nickname, LocalDateTime timeCreated, LocalDateTime timeDeleted, int accessId) {
        this.uid = uid;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.timeCreated = timeCreated;
        this.timeDeleted = timeDeleted;
        this.accessId = accessId;
    }

    public int getUid() {
        return uid;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeDeleted() {
        return timeDeleted;
    }

    public int getAccessId() {
        return accessId;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setTimeDeleted(LocalDateTime timeDeleted) {
        this.timeDeleted = timeDeleted;
    }

    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", timeCreated=" + timeCreated +
                ", timeDeleted=" + timeDeleted +
                ", accessId=" + accessId +
                '}';
    }
}
