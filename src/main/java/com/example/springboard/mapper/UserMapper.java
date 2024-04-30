package com.example.springboard.mapper;

import com.example.springboard.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByLoginIdAndPw(String id, String pw);
    boolean checkLoginId(String id);
}
