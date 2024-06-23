package com.example.springboard.service;

import com.example.springboard.dto.request.UserRequest;
import com.example.springboard.dto.request.UserSignupRequest;
import com.example.springboard.dto.request.UserUpdateRequest;
import com.example.springboard.global.auth.Token;
import com.example.springboard.vo.User;

public interface UserService {
    Token login(UserRequest userRequest);
    void checkLoginID(String userId);

    void signUp(UserSignupRequest userRequest);

    void userUpdate(UserUpdateRequest userRequest);

    void userDelete(UserRequest userRequest);

    User getUserDetail(String userId);

    void logout(String accessToken, String refreshToken);

}
