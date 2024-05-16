package com.example.springboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class PostRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    public String title;
    @NotBlank(message = "내용을 입력해주세요.")
    public String content;

    public String guestId;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{4,10}$",
            message = "비밀번호는 4~10 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    public String guestPw;

    public String getTitle() {
        return title;
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

    @Override
    public String toString() {
        return "PostRequest{" +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", guestId='" + guestId + '\'' +
                ", guestPw='" + guestPw + '\'' +
                '}';
    }
}
