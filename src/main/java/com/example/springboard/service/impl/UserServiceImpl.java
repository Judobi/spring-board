package com.example.springboard.service.impl;

import com.example.springboard.service.UserService;
import com.example.springboard.vo.User;
import com.example.springboard.dto.UserRequest;
import com.example.springboard.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User login(UserRequest userRequest){
        return userMapper.loginIdAndPw(userRequest);
    }

    public boolean checkLoginID(String userId){
        return userMapper.checkLoginId(userId);
    }


    @Transactional
    public void signUp(UserRequest userRequest){
        userMapper.userSignUp(userRequest);
    }

    @Transactional
    public void userUpdate(UserRequest userRequest){
        userMapper.userUpdate(userRequest);
    }

    @Transactional
    public void userDelete(String userId){
        userMapper.userDelete(userId);
    }

    public User getUserDetail(String userId){
        return userMapper.getUserDetail(userId);
    }
}
