package com.dlteam2.server.Exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, 403, "unauthorized"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "internal server error"),
    REQUEST_VALUE_INVALID(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "입력값이 올바르지 않습니다"),
    ;


    private final HttpStatus status;
    private final int code;
    private String message;

    ExceptionEnum(HttpStatus status, int code){
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, int code, String message){
        this.status = status;
        this.code = code;
        this.message= message;
    }
}
