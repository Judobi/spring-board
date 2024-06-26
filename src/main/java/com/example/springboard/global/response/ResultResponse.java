package com.example.springboard.global.response;

public class ResultResponse<T> extends ResponseDto{
    private T data;


    public static <T> ResultResponse<T> of(ResultCode resultCode, T data){
        return new ResultResponse<>(resultCode, data);
    }

    public static <T> ResultResponse<T> of(ResultCode resultCode){
        return new ResultResponse<>(resultCode);
    }

    public ResultResponse(ResultCode resultCode) {
        super(resultCode.getStatus(), resultCode.getCode(), resultCode.getMessage());
    }

    public ResultResponse(ResultCode resultCode, T data) {
        super(resultCode.getStatus(), resultCode.getCode(), resultCode.getMessage());
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
