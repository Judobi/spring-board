package com.example.springboard.dto.request;

import jakarta.validation.constraints.Pattern;

public class CommentAuthRequest {
    private int commentNo;
    private int postNo;
    private int boardId;
    private Integer uid;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{4,10}$",
            message = "비밀번호는 4~10 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String guestPw;


    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public int getPostNo() {
        return postNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getGuestPw() {
        return guestPw;
    }

    public void setGuestPw(String guestPw) {
        this.guestPw = guestPw;
    }
}
