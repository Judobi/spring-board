package com.example.springboard.global.response;

import org.springframework.http.HttpStatus;

public class ResultResponse extends ResponseDto{
    private Object data;

    public static ResultResponse of(ResultCode resultCode, Object data){
        return new ResultResponse(resultCode, data);
    }

    public static ResultResponse of(ResultCode resultCode){
        return new ResultResponse(resultCode);
    }

    public ResultResponse(ResultCode resultCode) {
        super(resultCode.getStatus(), resultCode.getCode(), resultCode.getMessage());
    }

    public ResultResponse(ResultCode resultCode, Object data) {
        super(resultCode.getStatus(), resultCode.getCode(), resultCode.getMessage());
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
