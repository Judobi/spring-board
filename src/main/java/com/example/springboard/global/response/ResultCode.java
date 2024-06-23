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
    GET_POSTLIST_SUCCESS(HttpStatus.OK, "B001", "게시글 목록을 불러오는데 성공했습니다."),
    GET_POST_SUUCCESS(HttpStatus.OK, "B002", "게시글을 불러오는데 성공했습니다."),
    INSERT_POST_SUCCESS(HttpStatus.CREATED, "B003", "게시글 등록을 성공했습니다."),
    UPDATE_POST_SUCCESS(HttpStatus.OK, "B004", "게시글 수정을 성공했습니다."),
    DELETE_POST_SUCCESS(HttpStatus.OK, "B005", "게시글 삭제를 성공했습니다"),
    CHECK_POSTPW_SUCCESS(HttpStatus.OK, "B006", "게시글 비밀번호가 일치합니다"),

    //comment
    GET_COMMENTLIST_SUCCESS(HttpStatus.OK, "C001", "댓글 목록을 불러오는데 성공했습니다."),
    INSERT_COMMENT_SUCCESS(HttpStatus.CREATED, "C003", "댓글 등록을 성공했습니다."),
    UPDATE_COMMENT_SUCCESS(HttpStatus.OK, "C004", "댓글 수정을 성공했습니다."),
    DELETE_COMMENT_SUCCESS(HttpStatus.OK, "C005", "댓글 삭제를 성공했습니다"),
    CHECK_COMMENT_SUCCESS(HttpStatus.OK, "C006", "댓글 비밀번호가 일치합니다");

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
