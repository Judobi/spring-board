package com.example.springboard.service;

import com.example.springboard.global.auth.Token;

public interface TokenService {

    Token reissueAccessToken(String refreshToken);
}
