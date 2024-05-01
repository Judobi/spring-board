package com.example.springboard.service;

import com.example.springboard.dto.User;
import com.example.springboard.dto.UserRequest;
import com.example.springboard.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User login(String id, String pw){
        return userMapper.findByLoginIdAndPw(id, pw);
    }

    public boolean checkLoginID(String id){
        return userMapper.checkLoginId(id);
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
    public void userDelete(String user_id){
        userMapper.deleteUser(user_id);
    }
}
