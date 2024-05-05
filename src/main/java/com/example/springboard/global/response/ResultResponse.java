package com.example.springboard.global.response;

import org.springframework.http.HttpStatus;

public class ResultResponse {
    private HttpStatus status;
    private String code;
    private String message;
    private Object data;

    public static ResultResponse of(ResultCode resultCode, Object data){
        return new ResultResponse(resultCode, data);
    }

    public static ResultResponse of(ResultCode resultCode){
        return new ResultResponse(resultCode);
    }

    public ResultResponse(ResultCode resultCode) {
        this.status = resultCode.getStatus();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public ResultResponse(ResultCode resultCode, Object data) {
        this.status = resultCode.getStatus();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
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

    public Object getData() {
        return data;
    }
}
