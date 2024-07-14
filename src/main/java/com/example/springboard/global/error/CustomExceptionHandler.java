package com.example.springboard.global.error;

import com.example.springboard.global.error.exception.ApiException;
import io.jsonwebtoken.JwtException;
import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    // 비즈니스 로직에서 발생하는 예외
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleCommonApiException(ApiException ex){
        final ErrorResponse response = ErrorResponse.of(ex.getErrorCode(), ex.getErrors());
        return createResponse(ex, response);
    }

    // jwt 잘못된 토큰일 경우 발생
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> handleJwtException(JwtException ex){
        return createResponse(ex, ErrorCode.NOT_FOUND_EXCEPTION);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException ex){
        return createResponse(ex, ErrorCode.NOT_FOUND_EXCEPTION);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<Object> illegalAccessException(IllegalAccessException ex){
        return createResponse(ex, ErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        return createResponse(ex, ErrorCode.INTERNAL_SERVER_ERROR);
    }



    // @Valid 에서 발생하는 바인딩 에러 (@RequestBody)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, ex.getBindingResult());
        return createResponse(ex, response);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return createResponse(ex, ErrorCode.NOT_FOUND_EXCEPTION);
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return createResponse(ex, ErrorCode.NOT_FOUND_EXCEPTION);
    }


    /**
     * 에러 응답 생성
     * 예외에 따라 예외가 발생한 부분들에 대한 설명 등, 추가적인 데이터를 같이 보내주는 경우, response를 받는다.
     * @param response 응답해줄 내용.
     */
    private ResponseEntity<Object> createResponse(Exception e, ErrorResponse response){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * 에러 응답 생성
     * response에 데이터 없이 응답만 보내는 경우, 일괄적으로 이 메서드에서 response 생성.
     * @param errorCode 사전에 정의한 에러코드
     */
    private ResponseEntity<Object> createResponse(Exception e, ErrorCode errorCode){
        log.error(e.getMessage(), e);
        final ErrorResponse response = ErrorResponse.of(errorCode, e.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
