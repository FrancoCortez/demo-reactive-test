package com.example.demo.repository;

import com.example.demo.dto.TypeUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

@DataMongoTest
public class TypeUserRepositoryTest {

    @Autowired
    private TypeUserRepository typeUserRepository;

    @BeforeTestExecution
    public void setUp () {
        this.typeUserRepository.deleteAll().subscribe();
    }

    @Test
    public void createTypeUser() throws Exception  {
        Flux<TypeUserDto> createObjects = Flux.just(TypeUserDto.builder()
                        .name("test")
                        .description("test description")
                        .build(),
                TypeUserDto.builder()
                        .name("test2")
                        .description("test2 description")
                        .build());
        Flux<TypeUserDto> testing = this.typeUserRepository.deleteAll()
                .thenMany(this.typeUserRepository.saveAll(createObjects))
                .thenMany(this.typeUserRepository.findAll());
        Predicate<TypeUserDto> predicate = o -> (o.getName().equalsIgnoreCase("test") || o.getName().equalsIgnoreCase("test2") && o.getId() != null);
        StepVerifier
                .create(testing)
                .expectNextMatches(predicate)
                .expectNextMatches(predicate)
                .verifyComplete();

    }

    @Test
    public void createTypeUserObjectNull() throws Exception {
        Flux<TypeUserDto> createObjects = Flux.empty();
        Flux<TypeUserDto> testing = this.typeUserRepository.deleteAll()
                .thenMany(this.typeUserRepository.saveAll(createObjects))
                .thenMany(this.typeUserRepository.findAll());
        StepVerifier
                .create(testing)
                .expectNextCount(0)
                .verifyComplete();

    }

    @Test
    public void findByName() {
        Flux<TypeUserDto> createObjects = Flux.just(TypeUserDto.builder()
                .name("test")
                .description("test description")
                .build());
        Flux<TypeUserDto> testing = this.typeUserRepository.deleteAll()
                .thenMany(this.typeUserRepository.saveAll(createObjects))
                .thenMany(this.typeUserRepository.findByName("test"));
        Predicate<TypeUserDto> predicate = t -> (t.getId() != null &&
                t.getName() != null &&
                t.getDescription() != null &&
                t.getName().equalsIgnoreCase("test") &&
                t.getDescription().equalsIgnoreCase("test description"));
        StepVerifier
                .create(testing)
                .expectNextMatches(predicate)
                .verifyComplete();
    }
}
