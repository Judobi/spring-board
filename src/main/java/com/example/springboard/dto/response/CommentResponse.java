package com.example.springboard.dto.response;

import java.time.LocalDateTime;

public class CommentResponse {
    private int commentNo;
    private int uid;
    private int postNo;

    private String nickname;
    private String content;
    private String guestId;

    private LocalDateTime timeCreated;
    private LocalDateTime timeModified;

    public int getCommentNo() {
        return commentNo;
    }

    public int getUid() {
        return uid;
    }

    public int getPostNo() {
        return postNo;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }

    public String getGuestId() {
        return guestId;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeModified() {
        return timeModified;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setTimeModified(LocalDateTime timeModified) {
        this.timeModified = timeModified;
    }

    @Override
    public String toString() {
        return "CommentResponse{" +
                "commentNo=" + commentNo +
                ", uid=" + uid +
                ", postNo=" + postNo +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", guestId='" + guestId + '\'' +
                ", timeCreated=" + timeCreated +
                ", timeModified=" + timeModified +
                '}';
    }
}
