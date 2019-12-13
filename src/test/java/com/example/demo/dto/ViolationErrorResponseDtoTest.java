package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ViolationErrorResponseDtoTest {

    @Test
    public void createObjectTest() throws Exception {
        ViolationErrorResponseDto object = ViolationErrorResponseDto.builder()
                .field("test name")
                .violation("description test")
                .build();
        assertAll("dto",
                () -> assertNotNull(object),
                () -> assertEquals("test name", object.getField()),
                () -> assertEquals("description test", object.getViolation())

        );
    }

    @Test
    public void createObjectNotAllConstructor() throws Exception {
        ViolationErrorResponseDto object = new ViolationErrorResponseDto();
        assertAll("dto",
                () -> assertNotNull(object),
                () -> assertNull(object.getField()),
                () -> assertNull(object.getViolation())

        );
    }

}
