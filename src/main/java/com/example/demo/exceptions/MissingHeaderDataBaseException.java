package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;


public class MissingHeaderDataBaseException extends GlobalException {

    public MissingHeaderDataBaseException(HttpStatus status, String message) {
        super(status, message);
    }

    public MissingHeaderDataBaseException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }

    public MissingHeaderDataBaseException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public MissingHeaderDataBaseException(String message, Throwable e) {
        super(HttpStatus.BAD_REQUEST, message, e);
    }
}
