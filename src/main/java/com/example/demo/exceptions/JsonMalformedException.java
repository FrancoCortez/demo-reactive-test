package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;


public class JsonMalformedException extends GlobalException {

    public JsonMalformedException(HttpStatus status, String message) {
        super(status, message);
    }

    public JsonMalformedException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }

    public JsonMalformedException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public JsonMalformedException(String message, Throwable e) {
        super(HttpStatus.BAD_REQUEST, message, e);
    }
}
