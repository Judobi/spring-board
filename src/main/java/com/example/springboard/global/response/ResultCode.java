package com.example.springboard.global.response;

import org.springframework.http.HttpStatus;

public enum ResultCode {
    //auth
    TOKEN_REISSUE_SUCCESS(HttpStatus.CREATED, "A001", "토큰 갱신을 성공했습니다."),

    //user
    LOGIN_SUCCESS(HttpStatus.OK, "U001", "로그인 되었습니다."),
    ID_USING_AVAIABLE(HttpStatus.OK, "U002", "사용가능한 아이디입니다."),
    SIGNUP_SUCCESS(HttpStatus.CREATED, "U003", "회원가입이 완료되었습니다."),
    SEARCH_MYINFO_SUCCESS(HttpStatus.OK, "U004", "회원정보 조회 성공"),
    CHANGE_PW_SUCCESS(HttpStatus.OK, "U005", "비밀번호 변경 성공"),
    DELETE_USER_SUCCESS(HttpStatus.OK, "U006", "회원탈퇴가 완료되었습니다"),
    LOGOUT_SUCCESS(HttpStatus.OK, "U007", "로그아웃 되었습니다"),

    //boaard
    GET_POSTLIST_SUCCESS(HttpStatus.OK, "B001", "게시물 목록을 불러오는데 성공했습니다.");

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
