package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;


public class GlobalException extends ResponseStatusException {
    public static final String EXCEPTION = "exception";
    public static final String MESSAGE = "message";
    public static final String STATUS = "status";
    public static final String ERROR = "error";
    public static final String CLASS = "class";
    public static final String METHOD = "method";

    public GlobalException(HttpStatus status, String message) {
        super(status, message);
    }

    public GlobalException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }


    public Map<String, Object> getProperties() {
        Map<String, Object> map = new HashMap<>();
        map.put(CLASS, this.getStackTrace()[0].getClassName());
        map.put(METHOD, this.getStackTrace()[0].getMethodName());
        map.put(EXCEPTION, this.getClass().getSimpleName());
        map.put(MESSAGE, this.getMessage());
        map.put(STATUS, this.getStatus().value());
        map.put(ERROR, this.getStatus().getReasonPhrase());
        return map;
    }
}
