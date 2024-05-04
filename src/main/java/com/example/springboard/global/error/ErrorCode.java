package com.example.springboard.global.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    //Common
    INVALID_INPUT_VALUE("C001", HttpStatus.BAD_REQUEST, "invalid input type"),
    //Auth

    //User
    USER_ID_ALREADY_EXIST("U001", HttpStatus.CONFLICT, "해당 아이디가 이미 존재합니다");

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
