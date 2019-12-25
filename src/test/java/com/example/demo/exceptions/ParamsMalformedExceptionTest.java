package com.example.demo.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParamsMalformedExceptionTest {
    @Test
    public void createObjectTest() throws Exception {
        ParamsMalformedException object = new ParamsMalformedException(HttpStatus.OK, "test ok");
        assertAll("dto",
                () -> assertNotNull(object),
                () -> assertEquals("200 OK \"test ok\"", object.getMessage()),
                () -> assertEquals(HttpStatus.OK, object.getStatus())
        );
    }

    @Test
    public void createObjectTestWithThrow() throws Exception {
        Exception ex = new Exception("ola");
        ParamsMalformedException object = new ParamsMalformedException(HttpStatus.BAD_REQUEST, "ola", ex);
        assertAll("dto",
                () -> assertNotNull(object),
                () -> assertEquals("400 BAD_REQUEST \"ola\"; nested exception is java.lang.Exception: ola", object.getMessage()),
                () -> assertEquals(HttpStatus.BAD_REQUEST, object.getStatus())
        );
    }

    @Test
    public void createObjectTestWithMessage() throws Exception {
        ParamsMalformedException object = new ParamsMalformedException("ola");
        assertAll("dto",
                () -> assertNotNull(object),
                () -> assertEquals("400 BAD_REQUEST \"ola\"", object.getMessage()),
                () -> assertEquals(HttpStatus.BAD_REQUEST, object.getStatus())
        );
    }

    @Test
    public void createObjectTestWithMessageWithException() throws Exception {
        Exception ex = new Exception("ola");
        ParamsMalformedException object = new ParamsMalformedException("ola", ex);

        assertAll("dto",
                () -> assertNotNull(object),
                () -> assertEquals("400 BAD_REQUEST \"ola\"; nested exception is java.lang.Exception: ola", object.getMessage()),
                () -> assertEquals(HttpStatus.BAD_REQUEST, object.getStatus())
        );
    }

    @Test
    public void getPropertiesTest() throws Exception {
        ParamsMalformedException object = new ParamsMalformedException(HttpStatus.OK, "test ok");
        Map<String, Object> a = object.getProperties();
        assertAll("tesing",
                () -> assertEquals(200, a.get("status")),
                () -> assertEquals("200 OK \"test ok\"", a.get("message"))
        );

    }


}
