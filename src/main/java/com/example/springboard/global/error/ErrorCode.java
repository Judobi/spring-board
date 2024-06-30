package com.example.springboard.global.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    //Common
    INVALID_INPUT_VALUE("C001", HttpStatus.BAD_REQUEST, "invalid input type"),
    NOT_FOUND_EXCEPTION("C002", HttpStatus.NOT_FOUND, "Not Found exception"),
    BAD_REQUEST("C003", HttpStatus.BAD_REQUEST, "Bad Request"),
    INTERNAL_SERVER_ERROR("C004", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),

    //Auth
    TOKEN_INVALID_ERROR("A001", HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED_ERROR("A002", HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),

    //User
    USER_ID_ALREADY_EXIST("U001", HttpStatus.CONFLICT, "해당 아이디가 이미 존재합니다"),
    USER_LOGIN_FAIL("U002", HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 틀렸습니다."),
    USER_STATUS_DELETED("U003", HttpStatus.BAD_REQUEST, "탈퇴한 회원입니다."),
    USER_PW_FAIL("U004", HttpStatus.BAD_REQUEST, "비밀번호가 틀립니다."),
    USER_NOT_EXIST("U005", HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),

    //Board
    BOARD_AUTH_USER_FAIL("B001", HttpStatus.UNAUTHORIZED, "이 게시판은 회원 게시판입니다. 로그인해 주세요."),
    BOARD_AUTH_NONUSER_FAIL("B002", HttpStatus.FORBIDDEN, "이 게시판은 회원은 이용할 수 없는 비회원 게시판입니다. 로그아웃 해주세요."),
    BOARD_AUTH_FORBIDDEN("B003", HttpStatus.FORBIDDEN, "접근할 수 없는 게시판입니다."),

    //Post
    POST_GET_FAIL("P001", HttpStatus.NOT_FOUND, "요청한 게시글을 찾을 수 없습니다"),
    POST_PWCHECK_FAIL("P002", HttpStatus.FORBIDDEN, "게시글 비밀번호가 일치하지 않습니다."),
    POST_CHANGE_AUTH_FAIL("P003", HttpStatus.FORBIDDEN, "게시글 작성자와 유저정보가 일치하지 않습니다."),
    POST_STATUS_DELETE("P004", HttpStatus.FORBIDDEN, "삭제된 게시글은 수정/삭제 할 수 없습니다."),
    POST_USER_NOT_ACCESS("P005", HttpStatus.FORBIDDEN, "탈퇴한 유저는 게시글을 수정/삭제 할 수 없습니다."),

    //Comment
    COMMENT_URL_FAIL("C000", HttpStatus.BAD_REQUEST, "잘못된 요청입니다. 다시 확인해주세요."),
    COMMENT_POST_STATUS_FAIL("C001", HttpStatus.FORBIDDEN, "삭제된 게시글입니다."),
    COMMENT_STATUS_DELETED("C002", HttpStatus.FORBIDDEN, "삭제된 댓글입니다."),
    COMMENT_INSERT_IDPW_BLANK("C003", HttpStatus.BAD_REQUEST, "비회원 댓글 작성 시, guestId와 guestPw 는 공백일 수 없습니다."),
    COMMENT_PWCHECK_FAIL("C004", HttpStatus.FORBIDDEN, "댓글 비밀번호가 일치하지 않습니다."),
    COMMENT_UIDCHECK_FAIL("C005", HttpStatus.FORBIDDEN, "댓글 작성자와 유저정보가 일치하지 않습니다."),
    COMMENT_INFO_GET_FAIL("C006", HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다.");


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
