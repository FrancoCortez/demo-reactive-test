package com.example.demo.dto.domain;

import com.example.demo.model.domain.TypeUserModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TypeUserModelTest {

    @Test
    public void createObjectTest() throws Exception {
        TypeUserModel object = TypeUserModel.builder()
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
