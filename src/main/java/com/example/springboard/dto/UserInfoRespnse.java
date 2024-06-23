package com.example.springboard.dto;

import com.example.springboard.vo.User;

import java.time.LocalDateTime;

public class UserInfoRespnse {
    private String id;
    private String nickname;
    private LocalDateTime createTime;

    public UserInfoRespnse(User user) {
        this.id = user.getUserId();
        this.nickname = user.getNickname();
        this.createTime = user.getTimeCreated();
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}
