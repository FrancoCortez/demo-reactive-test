package com.example.demo.service.base;

import com.example.demo.model.base.BaseIdModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface BaseService<T extends BaseIdModel, ID extends UUID> {

    Mono<T> create(T object);

    Flux<T> createAll(Flux<T> objects);

    Mono<T> update(T object, ID id);

    Mono<Void> deleteById(ID id);

    Mono<Void> deleteAll();

    Flux<T> findAll();

    Mono<T> findById(ID id);

    Flux<T> findByAllId(List<ID> ids);
}
