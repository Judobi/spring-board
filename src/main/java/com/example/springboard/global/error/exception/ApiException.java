package com.example.springboard.global.error.exception;

import com.example.springboard.global.error.ErrorCode;
import com.example.springboard.global.error.ErrorResponse;

import java.util.ArrayList;
import java.util.List;

public class ApiException extends RuntimeException{
    private ErrorCode errorCode;
    private List<ErrorResponse.FieldError> errors = new ArrayList<>();

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode, List<ErrorResponse.FieldError> errors) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public List<ErrorResponse.FieldError> getErrors() {
        return errors;
    }
}
