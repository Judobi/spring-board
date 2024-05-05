package com.example.springboard.global.error;

import com.example.springboard.global.error.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler{
    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    // @Valid 에서 발생하는 Bindin 에러 (@RequestBody)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindingException(BindException ex){
        log.error("binding error: ", ex);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, ex.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    // 비즈니스 로직에서 발생하는 예외
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleCommonApiException(ApiException ex){
        log.error("ApiException: ", ex);
        final ErrorCode errorCode = ex.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode, ex.getErrors());
        return new ResponseEntity<>(response, errorCode.getStatus());
    }

}
