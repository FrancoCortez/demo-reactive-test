package com.example.demo.repository.domain;

import com.example.demo.model.domain.TypeUserModel;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TypeUserRepository extends ReactiveCassandraRepository<TypeUserModel, UUID> {

    Mono<TypeUserModel> findByName(String name);
}
