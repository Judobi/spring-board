package com.example.springboard.controller;

import com.example.springboard.global.auth.Token;
import com.example.springboard.global.auth.TokenProvider;
import com.example.springboard.global.error.ErrorCode;
import com.example.springboard.global.error.exception.ApiException;
import com.example.springboard.global.response.ResultCode;
import com.example.springboard.global.response.ResultResponse;
import com.example.springboard.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    public final TokenService tokenService;
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/reissue-access")
    public ResponseEntity<ResultResponse> reissueAccessToken(@RequestHeader(value = TokenProvider.REFRESH_HEADER_STRING, required = false) String refreshToken){
        Token token = tokenService.reissueAccessToken(refreshToken);

        // 만료된 토큰
        if(token == null){
            throw new ApiException(ErrorCode.TOKEN_EXPIRED_ERROR);
        }
        ResultResponse response = ResultResponse.of(ResultCode.TOKEN_REISSUE_SUCCESS, token);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
