package com.example.springboard.vo;

import com.example.springboard.dto.request.CommentDeleteRequest;
import com.example.springboard.dto.request.CommentRequest;

import java.time.LocalDateTime;

public class Comment {
    private int postNo;
    private int commentNo;
    private Integer uid;
    private String content;
    private String guestId;
    private String guestPw;
    private LocalDateTime timeCreated;
    private LocalDateTime timeModified;
    private LocalDateTime timeDeleted;

    public Comment() {
    }

    // 댓글 등록, 수정 시 사용
    public Comment(CommentRequest request){
        this.postNo = request.getPostNo();
        this.uid = request.getUid();
        this.content = request.getContent();

        // url로 직접 요청할 경우 -  uid가 있는 회원댓글 등록에도 guest id, pw를 같이 보낼경우 db에 저장되는 경우 방지
        if(request.getUid()==null) {
            this.guestId = request.getGuestId();
            this.guestPw = request.getGuestPw();
        }

        // 댓글 수정시
        if(request.getCommentNo() != null){
            this.commentNo = request.getCommentNo();
        }
    }

    // 댓글 삭제 시 사용
    public Comment(CommentDeleteRequest request){
        this.postNo = request.getPostNo();
        this.uid = request.getUid();
        this.commentNo = request.getCommentNo();

        // url로 직접 요청할 경우 -  uid가 있는 회원댓글 등록에도 guest id, pw를 같이 보낼경우 db에 저장되는 경우 방지
        if(request.getUid()==null) {
            this.guestPw = request.getGuestPw();
        }
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
        return "Comment{" +
                "postNo=" + postNo +
                ", commentNo=" + commentNo +
                ", uid=" + uid +
                ", content='" + content + '\'' +
                ", guestId='" + guestId + '\'' +
                ", guestPw='" + guestPw + '\'' +
                ", timeCreated=" + timeCreated +
                ", timeModified=" + timeModified +
                ", timeDeleted=" + timeDeleted +
                '}';
    }
}
