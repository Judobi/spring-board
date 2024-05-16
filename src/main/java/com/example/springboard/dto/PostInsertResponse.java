package com.example.springboard.dto;

import com.example.springboard.vo.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PostInsertResponse {
    public int postNo;
    public int boardNo;
    public int uid;
    public String title;
    public String content;
    public String guestId;

    public PostInsertResponse(){};
    public PostInsertResponse(Post post){
        this.boardNo = post.getBoardNo();
        this.postNo = post.getPostNo();
        this.uid = post.getUid();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.guestId = post.getGuestId();
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }
}
