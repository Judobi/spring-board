package com.example.springboard.mapper;

import com.example.springboard.dto.request.UserSignupRequest;
import com.example.springboard.dto.request.UserUpdateRequest;
import com.example.springboard.vo.User;
import com.example.springboard.dto.request.UserRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findIdAndPw(UserRequest userRequest);

    User getUserDetail(String userId);
    boolean checkLoginId(String userId);

    void userSignUp(UserSignupRequest userRequest);

    void userUpdate(UserUpdateRequest userRequest);

    void userDelete(UserRequest userRequest);

    User getUserByUid(Integer uid);
}
