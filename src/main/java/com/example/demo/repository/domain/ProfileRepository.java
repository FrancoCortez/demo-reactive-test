package com.example.demo.repository.domain;

import com.example.demo.model.domain.ProfileModel;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ProfileRepository extends ReactiveCassandraRepository<ProfileModel, UUID> {

    Mono<ProfileModel> findByName(String name);
}
