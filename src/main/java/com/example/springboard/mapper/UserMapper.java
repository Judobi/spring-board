package com.example.springboard.mapper;

import com.example.springboard.vo.User;
import com.example.springboard.dto.UserRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User loginIdAndPw(UserRequest userRequest);

    User getUserDetail(String userId);
    boolean checkLoginId(String userId);

    void userSignUp(UserRequest userRequest);

    void userUpdate(UserRequest userRequest);

    void userDelete(String user_id);
}
