package com.example.demo.service;

import com.example.demo.dto.TypeUserDto;
import com.example.demo.repository.TypeUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

@SpringBootTest
public class TypeUserServiceTest {

    @MockBean
    private TypeUserRepository typeUserRepository;
    @Autowired
    TypeUserService typeUserService;

    @Test
    public void createTypeUser() {
        TypeUserDto obj = TypeUserDto.builder()
                .id(null)
                .name("test")
                .description("test description")
                .build();
        Mockito.when(this.typeUserRepository.save(obj))
                .thenReturn(Mono.justOrEmpty(TypeUserDto.builder()
                        .id("1")
                        .name("test")
                        .description("test description")
                        .build()));
        Mono<TypeUserDto> typeUserDtoMono = this.typeUserService.create(obj);
        Predicate<TypeUserDto> predicate = p -> (p.getId() != null && p.getId().equalsIgnoreCase("1")) && (p.getName().equalsIgnoreCase("test") && p.getDescription().equalsIgnoreCase("test description"));
        StepVerifier
                .create(typeUserDtoMono)
                .expectNextMatches(predicate)
                .verifyComplete();
    }

    @Test
    public void findByName () {
        String test = "test";
        Mockito.when(this.typeUserRepository.findByName(test))
                .thenReturn(Mono.justOrEmpty(TypeUserDto.builder()
                        .id("1")
                        .name("test")
                        .description("test description")
                        .build()));
        Mono<TypeUserDto> typeUserDtoMono = this.typeUserService.findByName(test);
        Predicate<TypeUserDto> predicate = t -> (t.getId() != null &&
                t.getName() != null &&
                t.getDescription() != null &&
                t.getId().equalsIgnoreCase("1") &&
                t.getName().equalsIgnoreCase("test") &&
                t.getDescription().equalsIgnoreCase("test description")
        );
        StepVerifier
                .create(typeUserDtoMono)
                .expectNextMatches(predicate)
                .verifyComplete();
    }
}
