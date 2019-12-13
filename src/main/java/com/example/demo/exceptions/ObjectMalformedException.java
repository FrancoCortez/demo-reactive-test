package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;


public class ObjectMalformedException extends GlobalException {

    public ObjectMalformedException(HttpStatus status, String message) {
        super(status, message);
    }

    public ObjectMalformedException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }

    public ObjectMalformedException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public ObjectMalformedException(String message, Throwable e) {
        super(HttpStatus.BAD_REQUEST, message, e);
    }
}
