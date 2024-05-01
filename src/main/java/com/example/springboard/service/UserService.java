package com.example.springboard.service;

import com.example.springboard.dto.UserRequest;
import com.example.springboard.vo.User;

public interface UserService {
    User login(UserRequest userRequest);
    boolean checkLoginID(String userId);

    void signUp(UserRequest userRequest);

    void userUpdate(UserRequest userRequest);

    void userDelete(String userId);

    User getUserDetail(String userId);

}
