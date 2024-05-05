package com.example.springboard.service.impl;

import com.example.springboard.dto.UserSignupRequest;
import com.example.springboard.dto.UserUpdateRequest;
import com.example.springboard.global.error.ErrorCode;
import com.example.springboard.global.error.exception.ApiException;
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
        User user = userMapper.findIdAndPw(userRequest);
        if(user == null){
            throw new ApiException(ErrorCode.USER_LOGIN_FAIL);
        } else if (user.getTimeDeleted() != null) {
            throw new ApiException(ErrorCode.USER_STATUS_DELETED);
        }

        return user;
    }

    public void checkLoginID(String userId){
        if (userMapper.checkLoginId(userId)){
            throw new ApiException(ErrorCode.USER_ID_ALREADY_EXIST);
        }
    }

    @Transactional
    public void signUp(UserSignupRequest userRequest){
        userMapper.userSignUp(userRequest);
    }

    @Transactional
    public void userUpdate(UserUpdateRequest userRequest){
        // 비밀번호 일치여부 확인
        if(userMapper.findIdAndPw(new UserRequest(userRequest)) == null){
            throw new ApiException(ErrorCode.USER_PW_FAIL);
        }
        // 비밀번호 업데이트
        userMapper.userUpdate(userRequest);
    }

    @Transactional
    public void userDelete(UserRequest userRequest){
        // 비밀번호 일치여부 확인
        if(userMapper.findIdAndPw(userRequest) == null){
            throw new ApiException(ErrorCode.USER_PW_FAIL);
        }
        userMapper.userDelete(userRequest);
    }

    public User getUserDetail(String userId){
        return userMapper.getUserDetail(userId);
    }
}
