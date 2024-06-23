package com.example.springboard.global.error;

import com.example.springboard.global.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorResponse extends ResponseDto {
    private List<FieldError> errors;

    private ErrorResponse(ErrorCode errorCode){
        super(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
    }

    private ErrorResponse(ErrorCode errorCode, String message){
        super(errorCode.getStatus(), errorCode.getCode(), message);
    }

    private ErrorResponse(ErrorCode errorCode, List<FieldError> errors) {
        super(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
        this.errors = errors;
    }

    public static ErrorResponse of (final ErrorCode errorCode, final String message){
        return new ErrorResponse(errorCode, message);
    }

    public static ErrorResponse of (final ErrorCode errorCode, final BindingResult bindingResult){
        return new ErrorResponse(errorCode, FieldError.of(bindingResult));
    }

    public static ErrorResponse of (final ErrorCode errorCode){
        return new ErrorResponse(errorCode);
    }

    public static ErrorResponse of (final ErrorCode errorCode, final List<FieldError> errors){
        return new ErrorResponse(errorCode, errors);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                super.toString() +
                "errors=" + errors +
                '}';
    }

    public List<FieldError> getErrors() {
        return errors;
    }


    public static class FieldError{
        private String field;
        private String value;
        private String reason;

        public FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of (String field, String value, String reason){
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        public static List<FieldError> of (final BindingResult bindingResult){
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream().map(error -> new FieldError(
                    error.getField(),
                    error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                    error.getDefaultMessage()
            )).collect(Collectors.toList());
        }

        public String getField() {
            return field;
        }

        public String getValue() {
            return value;
        }

        public String getReason() {
            return reason;
        }

        @Override
        public String toString() {
            return "FieldError{" +
                    "field='" + field + '\'' +
                    ", value='" + value + '\'' +
                    ", reason='" + reason + '\'' +
                    '}';
        }
    }

}
