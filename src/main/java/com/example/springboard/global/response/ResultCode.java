package com.example.springboard.global.response;

import org.springframework.http.HttpStatus;

public enum ResultCode {
    //user
    LOGIN_SUCCESS(HttpStatus.OK, "U001", "로그인 되었습니다."),
    ID_USING_AVAIABLE(HttpStatus.OK, "U002", "사용가능한 아이디입니다.");

    private HttpStatus status;
    private String code;
    private String message;

    ResultCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
