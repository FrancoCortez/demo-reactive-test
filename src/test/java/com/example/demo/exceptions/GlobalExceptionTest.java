package com.example.demo.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionTest {

    @Test
    public void createObjectTest() throws Exception {
        GlobalException object = new GlobalException(HttpStatus.OK, "test ok");
        assertAll("dto",
                () -> assertNotNull(object),
                () -> assertEquals("200 OK \"test ok\"", object.getMessage()),
                () -> assertEquals(HttpStatus.OK, object.getStatus())
        );
        Exception exception = new Exception("testing exeception");
        GlobalException objectFull = new GlobalException(HttpStatus.BAD_REQUEST, "ola", exception );
        assertAll("dto",
                () -> assertNotNull(objectFull),
                () -> assertEquals("400 BAD_REQUEST \"ola\"; nested exception is java.lang.Exception: testing exeception", objectFull.getMessage()),
                () -> assertEquals(HttpStatus.BAD_REQUEST, objectFull.getStatus())
        );
    }

    @Test
    public void getPropertiesTest() throws Exception {
        GlobalException object = new GlobalException(HttpStatus.OK, "test ok");
        Map<String, Object> a = object.getProperties();
        assertAll("tesing",
                () -> assertEquals(200, a.get("status")),
                () -> assertEquals("200 OK \"test ok\"", a.get("message"))
        );
    }
}
