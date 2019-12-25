package com.example.demo.config.error;

import com.example.demo.exceptions.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Configuration
@Slf4j
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    private static final String EXCEPTION = "exception";
    private static final String MESSAGE = "message";
    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String CLASS = "class";
    private static final String METHOD = "method";
    private static final String SYSTEM_EXCEPTION = "SystemException";
    private static final String SYSTEM_ERROR_CHECK_LOGS = "System Error , Check logs!";
    private static final String SYSTEM_ERROR = " System Error ";
    private static final String VALUE = "500";

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
        if (getError(request) instanceof GlobalException) {
            GlobalException ex = (GlobalException) getError(request);
            return ex.getProperties();

        } else if (getError(request) instanceof ResponseStatusException) {
            ResponseStatusException ex = (ResponseStatusException) getError(request);
            map.put(CLASS, ex.getStackTrace()[0].getClassName());
            map.put(METHOD, ex.getStackTrace()[0].getMethodName());
            map.put(EXCEPTION, ex.getClass().getSimpleName());
            map.put(MESSAGE, ex.getMessage());
            map.put(STATUS, ex.getStatus().value());
            map.put(ERROR, ex.getStatus().getReasonPhrase());
            return map;
        }
        Exception ex = (Exception) getError(request);
        map.put(CLASS, ex.getStackTrace()[0].getClassName());
        map.put(METHOD, ex.getStackTrace()[0].getMethodName());
        map.put(EXCEPTION, SYSTEM_EXCEPTION);
        map.put(MESSAGE, SYSTEM_ERROR_CHECK_LOGS);
        map.put(STATUS, VALUE);
        map.put(ERROR, SYSTEM_ERROR);
        return map;
    }
}
