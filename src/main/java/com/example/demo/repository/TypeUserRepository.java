package com.example.demo.repository;

import com.example.demo.model.TypeUserModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TypeUserRepository extends ReactiveMongoRepository<TypeUserModel, String> {

    Mono<TypeUserModel> findByName(String name);
}
