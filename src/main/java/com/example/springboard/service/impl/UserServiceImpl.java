package com.example.springboard.service.impl;

import com.example.springboard.dto.UserSignupRequest;
import com.example.springboard.dto.UserUpdateRequest;
import com.example.springboard.global.auth.Token;
import com.example.springboard.global.auth.TokenProvider;
import com.example.springboard.global.error.ErrorCode;
import com.example.springboard.global.error.exception.ApiException;
import com.example.springboard.mapper.TokenMapper;
import com.example.springboard.service.UserService;
import com.example.springboard.vo.User;
import com.example.springboard.dto.UserRequest;
import com.example.springboard.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;
    private final TokenMapper tokenMapper;
    private final TokenProvider tokenProvider;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, TokenMapper tokenMapper, TokenProvider tokenProvider) {
        this.userMapper = userMapper;
        this.tokenMapper = tokenMapper;
        this.tokenProvider = tokenProvider;
    }

    public Token login(UserRequest userRequest){
        User user = userMapper.findIdAndPw(userRequest);
        if(user == null){
            throw new ApiException(ErrorCode.USER_LOGIN_FAIL);
        } else if (user.getTimeDeleted() != null) {
            throw new ApiException(ErrorCode.USER_STATUS_DELETED);
        }

        Token token = tokenProvider.createToken(user.getUid(), user.getNickname(), user.getAccessId());
        tokenMapper.saveRefreshToken(user.getUid(), token.getRefreshToken());
        return token;
    }

    public void logout(String accessToken, String refreshToken) {
        if (accessToken == null || refreshToken == null){
            throw new ApiException(ErrorCode.TOKEN_INVALID_ERROR);
        }
        tokenMapper.deleteRefreshToken(refreshToken);
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
