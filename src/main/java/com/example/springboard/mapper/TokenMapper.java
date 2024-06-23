package com.example.springboard.mapper;

import com.example.springboard.vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {
    User findByRefreshToken(String refershToken);
    void saveRefreshToken(int uid, String refreshToken);
    boolean deleteRefreshToken(String refreshToken);
}
