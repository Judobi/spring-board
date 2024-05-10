package com.example.springboard.vo;

import java.time.LocalDateTime;

public class Post {
    public int postNo;
    public int boardNo;
    public int uid;
    public String nickname;

    public String title;
    public String content;
    public int views;
    public int commentsCount;

    public String guestId;
    public String guestPw;
    public LocalDateTime timeCreated;
    public LocalDateTime timeModified;
    public LocalDateTime timeDeleted;



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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getGuestPw() {
        return guestPw;
    }

    public void setGuestPw(String guestPw) {
        this.guestPw = guestPw;
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

    public LocalDateTime getTimeDeleted() {
        return timeDeleted;
    }

    public void setTimeDeleted(LocalDateTime timeDeleted) {
        this.timeDeleted = timeDeleted;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postNo=" + postNo +
                ", boardNo=" + boardNo +
                ", uid=" + uid +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", views=" + views +
                ", commentsCount=" + commentsCount +
                ", guestId='" + guestId + '\'' +
                ", guestPw='" + guestPw + '\'' +
                ", timeCreated=" + timeCreated +
                ", timeModified=" + timeModified +
                ", timeDeleted=" + timeDeleted +
                '}';
    }
}
