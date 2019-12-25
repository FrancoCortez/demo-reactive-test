package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;


public class NotFoundException extends GlobalException {

    public NotFoundException(HttpStatus status, String message) {
        super(status, message);
    }

    public NotFoundException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public NotFoundException(String message, Throwable e) {
        super(HttpStatus.NOT_FOUND, message, e);
    }
}
