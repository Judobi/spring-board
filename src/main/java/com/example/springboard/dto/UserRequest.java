package com.example.springboard.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank(message = "아이디를 입력해주세요")
    public String userId;

    @NotBlank(message = "비밀번호를 입력해주세요")
    public String password;

    public String nickname;

    public UserRequest(){}

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public UserRequest(UserUpdateRequest userUpdateRequest){
        this.userId = userUpdateRequest.getUserId();
        this.password = userUpdateRequest.getPassword();
    }

    @Override
    public String toString() {
        return "UserSignUp{" +
                "user_id='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
