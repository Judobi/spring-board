package com.example.springboard.dto;

import java.time.LocalDateTime;

public class User {

    private int uid;
    private String user_id;
    private String nickname;
    private LocalDateTime time_created;
    private int access_id;

    public User(){}

    public User(int uid, String user_id, String nickname, LocalDateTime time_created, int access_id) {
        this.uid = uid;
        this.user_id = user_id;
        this.nickname = nickname;
        this.time_created = time_created;
        this.access_id = access_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getTime_created() {
        return time_created;
    }

    public void setTime_created(LocalDateTime time_created) {
        this.time_created = time_created;
    }

    public int getAccess_id() {
        return access_id;
    }

    public void setAccess_id(int access_id) {
        this.access_id = access_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", user_id='" + user_id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", time_created=" + time_created +
                ", access_id=" + access_id +
                '}';
    }
}
