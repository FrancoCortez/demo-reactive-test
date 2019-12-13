package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;


public class MissingParameterException extends GlobalException {

    public MissingParameterException(HttpStatus status, String message) {
        super(status, message);
    }

    public MissingParameterException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }

    public MissingParameterException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public MissingParameterException(String message, Throwable e) {
        super(HttpStatus.BAD_REQUEST, message, e);
    }
}
