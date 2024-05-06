package com.example.springboard.service.impl;

import com.example.springboard.global.auth.Token;
import com.example.springboard.global.auth.TokenProvider;
import com.example.springboard.mapper.TokenMapper;
import com.example.springboard.service.TokenService;
import com.example.springboard.vo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenServiceImpl implements TokenService {
    private final Logger log = LoggerFactory.getLogger(TokenService.class);
    private final TokenMapper tokenMapper;
    private final TokenProvider tokenProvider;

    public TokenServiceImpl(TokenMapper tokenMapper, TokenProvider tokenProvider) {
        this.tokenMapper = tokenMapper;
        this.tokenProvider = tokenProvider;
    }


    @Transactional
    public Token reissueAccessToken(String refreshToken) {
        //리프레시 토큰 디비조회
        User user = tokenMapper.findByRefreshToken(refreshToken);
        if(user == null){
            throw new JwtException("데이터베이스에 없는 잘못된 리프레시 토큰");
        }

        //리프레시 토큰 유료기간 확인
        Jws<Claims> claims = tokenProvider.getClaims(refreshToken);
        if(claims == null){
            tokenMapper.deleteRefreshToken(refreshToken);
            return null;
        }

        // 토큰 재발급
        String accessToken = tokenProvider.createAccessToken(user.getUid(), user.getNickname(), user.getAccessId());
        return new Token(accessToken, refreshToken);
    }
}
