package com.example.springboard.vo;

import com.example.springboard.dto.PostRequest;

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

    public Post() {
    }

    // 게시글 작성시 사용하는 생성자
    public Post(Integer uid, int boardId, PostRequest request){
        if(uid != null) {
            this.uid = uid;
        }
        this.boardNo = boardId;
        this.title = request.getTitle();
        this.content = request.getContent();
        this.guestId = request.getGuestId();
        this.guestPw = request.getGuestPw();
    }

    //게시글 수정시 사용하는 생성자
    public Post(Integer uid, int boardId, int postNo, PostRequest request){
        if(uid != null) {
            this.uid = uid;
        }
        this.boardNo = boardId;
        this.postNo = postNo;
        this.title = request.getTitle();
        this.content = request.getContent();
        this.guestPw = request.getGuestPw();
    }

    public int getPostNo() {
        return postNo;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public int getUid() {
        return uid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getViews() {
        return views;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getGuestPw() {
        return guestPw;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeModified() {
        return timeModified;
    }

    public LocalDateTime getTimeDeleted() {
        return timeDeleted;
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
