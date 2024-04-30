package com.example.springboard.global;

import org.springframework.http.HttpStatus;

public enum StatusCode {
    // 성공
    SUCCESS(HttpStatus.OK, "요청 성공했습니다."),
    LOGIN_ID_SUCCESS(HttpStatus.OK, "사용가능한 아이디입니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공했습니다."),
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료되었습니다"),

    // 실패
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 틀렸습니다"),
    LOGIN_ID_FAIL(HttpStatus.CONFLICT, "이미 사용중인 아이디입니다"),
    SIGNUP_FAIL(HttpStatus.BAD_REQUEST, "");

    private final HttpStatus status;
    private final String message;

    StatusCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
