package com.example.springboard.mapper;

import com.example.springboard.dto.User;
import com.example.springboard.dto.UserRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByLoginIdAndPw(UserRequest userRequest);

    User getUserDetail(String user_id);
    boolean checkLoginId(String user_id);

    void userSignUp(UserRequest userRequest);

    void userUpdate(UserRequest userRequest);

    void userDelete(String user_id);
}
