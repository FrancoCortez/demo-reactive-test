package com.example.demo.service;

import com.example.demo.model.domain.TypeUserModel;
import com.example.demo.repository.domain.TypeUserRepository;
import com.example.demo.service.domain.TypeUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;
import java.util.function.Predicate;

@SpringBootTest
public class TypeUserImplServiceTest {

    @MockBean
    private TypeUserRepository typeUserRepository;
    @Autowired
    private TypeUserService typeUserService;

    @Test
    public void createTypeUser() {
        TypeUserModel obj = TypeUserModel.builder()
                .name("test")
                .description("test description")
                .build();

        TypeUserModel objResp = TypeUserModel.builder()
                .name("test")
                .description("test description")
                .build();
        objResp.setId(UUID.fromString("1"));
        Mockito.when(this.typeUserRepository.save(obj))
                .thenReturn(Mono.justOrEmpty(objResp));
        Mono<TypeUserModel> TypeUserModelMono = this.typeUserService.create(obj);
        Predicate<TypeUserModel> predicate = p -> (p.getId() != null && p.getId().equals(UUID.fromString("1"))) && (p.getName().equalsIgnoreCase("test") && p.getDescription().equalsIgnoreCase("test description"));
        StepVerifier
                .create(TypeUserModelMono)
                .expectNextMatches(predicate)
                .verifyComplete();
    }

    @Test
    public void findByName() {
        String test = "test";
        TypeUserModel objReturn = TypeUserModel.builder()
                .name("test")
                .description("test description")
                .build();
        objReturn.setId(UUID.fromString("1"));
        Mockito.when(this.typeUserRepository.findByName(test))
                .thenReturn(Mono.justOrEmpty(objReturn));
        Mono<TypeUserModel> TypeUserModelMono = this.typeUserService.findByName(test);
        Predicate<TypeUserModel> predicate = t -> (t.getId() != null &&
                t.getName() != null &&
                t.getDescription() != null &&
                t.getId().equals(UUID.fromString("1")) &&
                t.getName().equalsIgnoreCase("test") &&
                t.getDescription().equalsIgnoreCase("test description")
        );
        StepVerifier
                .create(TypeUserModelMono)
                .expectNextMatches(predicate)
                .verifyComplete();
    }
}
