package com.example.demo.service.base.impl;

import com.example.demo.exceptions.MissingParameterException;
import com.example.demo.exceptions.ObjectMalformedException;
import com.example.demo.model.base.BaseIdModel;
import com.example.demo.service.base.BaseService;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.List;

public class BaseImplService<T extends BaseIdModel, ID extends String> implements BaseService<T, ID> {
    private ReactiveMongoRepository<T, ID> tidReactiveMongoRepository;
    private Class<T> type;

    public BaseImplService(ReactiveMongoRepository<T, ID> tidReactiveMongoRepository, Class<T> type) {
        this.tidReactiveMongoRepository = tidReactiveMongoRepository;
        this.type = type;
    }

    public Mono<T> create(T object) {
        if (object == null) {
            throw new ObjectMalformedException("El objecto de entrada es null");
        }
        return this.tidReactiveMongoRepository.save(object);
    }

    public Mono<T> update(T object, ID id) {
        if (object == null) {
            throw new ObjectMalformedException("El objecto de entrada es null");
        }
        if (id == null || id.isEmpty()) {
            throw new MissingParameterException("El parametro de busqueda para la edicion no es valido");
        }
        return this.tidReactiveMongoRepository.findById(id)
                .flatMap(map -> {
                    object.setId(map.getId());
                    return this.tidReactiveMongoRepository.save(object);
                });
    }

    public Mono<Void> deleteById(ID id) {
        if (id == null || id.isEmpty()) {
            throw new MissingParameterException("El parametro de busqueda es invalido");
        }
        return this.tidReactiveMongoRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return this.tidReactiveMongoRepository.deleteAll();
    }

    public Flux<T> findAll() {
        return this.tidReactiveMongoRepository.findAll();
    }

    public Mono<T> findById(ID id) {
        if (id == null || id.isEmpty()) {
            throw new MissingParameterException("El parametro de busqueda es invalido");
        }
        return this.tidReactiveMongoRepository.findById(id);
    }

    public Flux<T> findByAllId(List<ID> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new MalformedParameterizedTypeException("Las ids seleccionadas no son validas para la busqueda masiva");
        }
        return this.tidReactiveMongoRepository.findAllById(ids);
    }
}
