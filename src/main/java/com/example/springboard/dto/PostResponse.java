package com.example.springboard.dto;

import com.example.springboard.vo.Post;

import java.time.LocalDateTime;

public class PostResponse {
    private int postNo;
    private int boardNo;
    private String nickname;

    private String title;
    private int views;
    private int commentsCount;

    private String guestId;
    private LocalDateTime timeCreated;
    private LocalDateTime timeModified;

    public PostResponse(){}

    public PostResponse(Post post) {
        this.postNo = post.getPostNo();
        this.boardNo = post.getBoardNo();
        this.nickname = post.getNickname();
        this.title = post.getTitle();
        this.views = post.getViews() + 1; //게시글 조회 후, DB에 조회수를 증가를 업데이트 하기 때문에 +1
        this.commentsCount = post.getCommentsCount();
        this.guestId = post.getGuestId();
        this.timeCreated = post.getTimeCreated();
        this.timeModified = post.getTimeModified();
    }

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
