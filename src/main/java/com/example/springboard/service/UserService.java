package com.example.springboard.service;

import com.example.springboard.dto.UserRequest;
import com.example.springboard.vo.User;

public interface UserService {
    User login(UserRequest userRequest);
    boolean checkLoginID(String user_id);

    void signUp(UserRequest userRequest);

    void userUpdate(UserRequest userRequest);

    void userDelete(String user_id);

    User getUserDetail(String user_id);

}
