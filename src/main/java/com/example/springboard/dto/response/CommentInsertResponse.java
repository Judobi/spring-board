package com.example.springboard.dto.response;

import com.example.springboard.vo.Comment;

import java.time.LocalDateTime;

public class CommentInsertResponse {
    private int postNo;
    private int commentNo;
    private Integer uid;
    private String content;
    private String guestId;

    public CommentInsertResponse(Comment comment) {
        this.commentNo = comment.getCommentNo();
        this.postNo = comment.getPostNo();
        this.uid = comment.getUid();
        this.content = comment.getContent();
        this.guestId = comment.getGuestId();
    }

    public int getPostNo() {
        return postNo;
    }

    public int getCommentNo() {
        return commentNo;
    }

    public Integer getUid() {
        return uid;
    }

    public String getContent() {
        return content;
    }

    public String getGuestId() {
        return guestId;
    }


    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }



}
