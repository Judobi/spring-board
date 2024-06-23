package com.example.springboard.global.error;

import com.example.springboard.global.error.exception.ApiException;
import io.jsonwebtoken.JwtException;
import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class CustomExceptionHandler{
    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    // @Valid 에서 발생하는 Bindin 에러 (@RequestBody)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindingException(BindException ex){
        log.error(ex.getMessage(), ex);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, ex.getBindingResult());
        return new ResponseEntity<>(response, response.getStatus());
    }


    // 비즈니스 로직에서 발생하는 예외
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleCommonApiException(ApiException ex){
        log.error(ex.getMessage(), ex);
        final ErrorCode errorCode = ex.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode, ex.getErrors());
        return new ResponseEntity<>(response, response.getStatus());
    }

    // jwt 잘못된 토큰일 경우 발생
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException ex){
        log.error(ex.getMessage(), ex);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.TOKEN_INVALID_ERROR);
        return new ResponseEntity<>(response, response.getStatus());
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> noHandlerFoundException(NoHandlerFoundException ex){
        log.error(ex.getMessage(), ex);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_FOUND_EXCEPTION, ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException ex){
        log.error(ex.getMessage(), ex);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_FOUND_EXCEPTION, ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorResponse> illegalAccessException(IllegalAccessException ex){
        log.error(ex.getMessage(), ex);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        log.error(ex.getMessage(), ex);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
