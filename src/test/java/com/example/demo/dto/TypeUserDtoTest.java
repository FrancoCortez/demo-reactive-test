package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypeUserDtoTest {

    @Test
    public void createObjectTest() throws Exception {
        TypeUserDto object = TypeUserDto.builder()
                .id(null)
                .name("test name")
                .description("description test")
                .build();
        assertAll("dto",
                () -> assertNotNull(object),
                () -> assertNull(object.getId()),
                () -> assertEquals("test name", object.getName()),
                () -> assertEquals("description test", object.getDescription())

        );
    }

}
