package com.example.springboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserUpdateRequest {
    private String userId;

    @NotBlank(message = "변경할 비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String newPassword;

    private String password;

    private String nickname;

    public UserUpdateRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "UserUpdateRequest{" +
                "userId='" + userId + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
