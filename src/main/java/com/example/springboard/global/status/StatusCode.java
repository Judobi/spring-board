package com.example.springboard.global.status;

import org.springframework.http.HttpStatus;

public enum StatusCode {
    // 성공
    SUCCESS(HttpStatus.OK, "요청 성공했습니다."),
    LOGIN_ID_SUCCESS(HttpStatus.OK, "사용가능한 아이디입니다."),

    // 실패
    Login_ID_FAIL(HttpStatus.CONFLICT, "이미 사용중인 아이디입니다");

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
