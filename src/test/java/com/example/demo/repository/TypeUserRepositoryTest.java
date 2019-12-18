package com.example.demo.repository;

import com.example.demo.model.TypeUserModel;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
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
    public void setUp() {
        this.typeUserRepository.deleteAll().subscribe();
    }

    @Test
    public void createTypeUser() throws Exception {
        Publisher<TypeUserModel> createObjects = Flux.just(TypeUserModel.builder()
                        .name("test")
                        .description("test description")
                        .build(),
                TypeUserModel.builder()
                        .name("test2")
                        .description("test2 description")
                        .build());
        Flux<TypeUserModel> testing = this.typeUserRepository.deleteAll()
                .thenMany(this.typeUserRepository.saveAll(createObjects))
                .thenMany(this.typeUserRepository.findAll());
        Predicate<TypeUserModel> predicate = o -> (o.getName().equalsIgnoreCase("test") || o.getName().equalsIgnoreCase("test2") && o.getId() != null);
        StepVerifier
                .create(testing)
                .expectNextMatches(predicate)
                .expectNextMatches(predicate)
                .verifyComplete();

    }

    @Test
    public void createTypeUserObjectNull() throws Exception {
        Flux<TypeUserModel> createObjects = Flux.empty();
        Flux<TypeUserModel> testing = this.typeUserRepository.deleteAll()
                .thenMany(this.typeUserRepository.saveAll(createObjects))
                .thenMany(this.typeUserRepository.findAll());
        StepVerifier
                .create(testing)
                .expectNextCount(0)
                .verifyComplete();

    }

    @Test
    public void findByName() {
        Flux<TypeUserModel> createObjects = Flux.just(TypeUserModel.builder()
                .name("test")
                .description("test description")
                .build());
        Flux<TypeUserModel> testing = this.typeUserRepository.deleteAll()
                .thenMany(this.typeUserRepository.saveAll(createObjects))
                .thenMany(this.typeUserRepository.findByName("test"));
        Predicate<TypeUserModel> predicate = t -> (t.getId() != null &&
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
