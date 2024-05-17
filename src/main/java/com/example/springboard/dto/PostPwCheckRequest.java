package com.example.springboard.dto;

import jakarta.validation.constraints.Pattern;

public class PostPwCheckRequest {
    public int postNo;
    public int boardNo;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{4,10}$",
            message = "비밀번호는 4~10 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    public String guestPw;

    public int getPostNo() {
        return postNo;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public String getGuestPw() {
        return guestPw;
    }

    @Override
    public String toString() {
        return "PostPwCheckRequest{" +
                "postNo=" + postNo +
                ", boardNo=" + boardNo +
                ", guestPw='" + guestPw + '\'' +
                '}';
    }
}
