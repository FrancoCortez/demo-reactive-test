package com.example.demo.repository;

import com.example.demo.dto.TypeUserDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TypeUserRepository extends ReactiveMongoRepository<TypeUserDto, String> {

    Mono<TypeUserDto> findByName(String name);
}
