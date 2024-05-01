package com.example.springboard.mapper;

import com.example.springboard.dto.User;
import com.example.springboard.dto.UserRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByLoginIdAndPw(String id, String pw);
    boolean checkLoginId(String id);

    void userSignUp(UserRequest userRequest);

    void userUpdate(UserRequest userRequest);

    void userDelete(String user_id);
}
