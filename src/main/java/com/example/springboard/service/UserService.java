package com.example.springboard.service;

import com.example.springboard.dto.UserRequest;
import com.example.springboard.dto.UserSignupRequest;
import com.example.springboard.dto.UserUpdateRequest;
import com.example.springboard.global.auth.Token;
import com.example.springboard.vo.User;

public interface UserService {
    Token login(UserRequest userRequest);
    void checkLoginID(String userId);

    void signUp(UserSignupRequest userRequest);

    void userUpdate(UserUpdateRequest userRequest);

    void userDelete(UserRequest userRequest);

    User getUserDetail(String userId);

}
