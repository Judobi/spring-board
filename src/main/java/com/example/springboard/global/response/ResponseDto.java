package com.example.springboard.global.response;

import com.example.springboard.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

public class ResponseDto {
    private HttpStatus status;
    private String code;
    private String massage;

    public ResponseDto(HttpStatus status, String code, String massage) {
        this.status = status;
        this.code = code;
        this.massage = massage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", massage='" + massage + '\'' +
                '}';
    }
}
