package com.example.springboard.global.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    //Common
    INVALID_INPUT_VALUE("C001", HttpStatus.BAD_REQUEST, "invalid input type"),
    //Auth

    //User
    USER_ID_ALREADY_EXIST("U001", HttpStatus.CONFLICT, "해당 아이디가 이미 존재합니다"),
    USER_LOGIN_FAIL("U002", HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 틀렸습니다."),
    USER_STATUS_DELETED("U003", HttpStatus.BAD_REQUEST, "탈퇴한 회원입니다."),
    USER_PW_FAIL("U004", HttpStatus.BAD_REQUEST, "비밀번호가 틀립니다.");

    //Board

    //Post

    //Comment

    private final String code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
