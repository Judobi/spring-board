package com.example.springboard.dto;

import java.time.LocalDateTime;

public class PostListResponse {
    public int postNo;
    public int boardNo;
    public String nickname;

    public String title;
    public int views;
    public int commentsCount;

    public String guestId;
    public LocalDateTime timeCreated;
    public LocalDateTime timeModified;

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getTimeModified() {
        return timeModified;
    }

    public void setTimeModified(LocalDateTime timeModified) {
        this.timeModified = timeModified;
    }

    @Override
    public String toString() {
        return "PostListResponse{" +
                "postNo=" + postNo +
                ", boardNo=" + boardNo +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", views=" + views +
                ", commentsCount=" + commentsCount +
                ", guestId='" + guestId + '\'' +
                ", timeCreated=" + timeCreated +
                ", timeModified=" + timeModified +
                '}';
    }
}
