package com.example.springboard.mapper;

import com.example.springboard.dto.User;
import com.example.springboard.dto.UserSignUp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByLoginIdAndPw(String id, String pw);
    boolean checkLoginId(String id);

    void userSignUp(UserSignUp userSignUp);
}
