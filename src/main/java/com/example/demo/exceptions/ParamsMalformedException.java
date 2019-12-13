package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;


public class ParamsMalformedException extends GlobalException {

    public ParamsMalformedException(HttpStatus status, String message) {
        super(status, message);
    }

    public ParamsMalformedException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }

    public ParamsMalformedException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public ParamsMalformedException(String message, Throwable e) {
        super(HttpStatus.BAD_REQUEST, message, e);
    }
}
