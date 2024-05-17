package com.example.springboard.dto;

import jakarta.validation.constraints.Pattern;

public class PostDeleteRequest {
    public int boardNo;
    public int postNo;
    public Integer uid;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{4,10}$",
            message = "비밀번호는 4~10 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    public String guestPw;

    public int getPostNo() {
        return postNo;
    }

    public Integer getUid() {
        return uid;
    }

    public String getGuestPw() {
        return guestPw;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public void setPostNo(int postNo) {
        this.postNo = postNo;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setGuestPw(String guestPw) {
        this.guestPw = guestPw;
    }

    @Override
    public String toString() {
        return "PostDeleteRequest{" +
                "boardNo=" + boardNo +
                ", postNo=" + postNo +
                ", uid=" + uid +
                ", guestPw='" + guestPw + '\'' +
                '}';
    }
}
