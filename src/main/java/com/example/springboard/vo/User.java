package com.example.springboard.vo;

import java.time.LocalDateTime;

public class User {

    private int uid;
    private String userId;
    private String nickname;
    private LocalDateTime timeCreated;
    private int accessId;

    public User(){}

    public User(int uid, String userId, String nickname, LocalDateTime timeCreated, int accessId) {
        this.uid = uid;
        this.userId = userId;
        this.nickname = nickname;
        this.timeCreated = timeCreated;
        this.accessId = accessId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getAccessId() {
        return accessId;
    }

    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", user_id='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", time_created=" + timeCreated +
                ", access_id=" + accessId +
                '}';
    }
}
