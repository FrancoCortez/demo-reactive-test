package com.example.demo.service.base;

import com.example.demo.model.base.BaseIdModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BaseService<T extends BaseIdModel, ID extends String> {

    Mono<T> create(T object);

    Mono<T> update(T object, ID id);

    Mono<Void> deleteById(ID id);

    Mono<Void> deleteAll();

    Flux<T> findAll();

    Mono<T> findById(ID id);

    Flux<T> findByAllId(List<ID> ids);
}
