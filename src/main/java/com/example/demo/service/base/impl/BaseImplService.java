package com.example.demo.service.base.impl;

import com.example.demo.exceptions.MissingParameterException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.ObjectMalformedException;
import com.example.demo.model.base.BaseIdModel;
import com.example.demo.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
public class BaseImplService<T extends BaseIdModel, ID extends UUID> implements BaseService<T, ID> {
    private ReactiveCassandraRepository<T, ID> tidReactiveCassandraRepository;
    private Class<T> type;

    public BaseImplService(ReactiveCassandraRepository<T, ID> tidReactiveCassandraRepository, Class<T> type) {
        this.tidReactiveCassandraRepository = tidReactiveCassandraRepository;
        this.type = type;
    }

    public Mono<T> create(T object) {
        if (object == null) throw new ObjectMalformedException("El objecto de entrada es null");
        object.setId(UUID.randomUUID());
        object.setCreatedDate(LocalDateTime.now());
        object.setCreateBy("LEGACY");
        return this.tidReactiveCassandraRepository.save(object);
    }

    public Flux<T> createAll(Flux<T> objects) {
        if (objects == null) throw new ObjectMalformedException("La lista de entidades se encuentra vacia");
        return this.tidReactiveCassandraRepository.saveAll(objects.map(mapper -> {
            mapper.setId(UUID.randomUUID());
            mapper.setCreatedDate(LocalDateTime.now());
            mapper.setCreateBy("LEGACY");
            return mapper;
        }));
    }

    public Mono<T> update(T object, ID id) {
        if (object == null) throw new ObjectMalformedException("El objecto de entrada es null");
        if (id == null)
            throw new MissingParameterException("El parametro de busqueda para la edicion no es valido");
        return this.findById(id)
                .flatMap(map -> {
                    object.setId(map.getId());
                    object.setCreateBy(map.getCreateBy());
                    object.setCreatedDate(map.getCreatedDate());
                    return this.tidReactiveCassandraRepository.save(object);
                });
    }

    public Mono<Void> deleteById(ID id) {
        if (id == null) throw new MissingParameterException("El parametro de busqueda es invalido");
        return this.tidReactiveCassandraRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return this.tidReactiveCassandraRepository.deleteAll();
    }

    public Flux<T> findAll() {
        return this.tidReactiveCassandraRepository.findAll();
    }

    public Mono<T> findById(ID id) {
        if (id == null) throw new MissingParameterException("El parametro de busqueda es invalido");
        return this.tidReactiveCassandraRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundException("El id [" + id + "] no se encontro dentro de la base de datos"))));
    }

    public Flux<T> findByAllId(List<ID> ids) {
        if (ids == null || ids.isEmpty())
            throw new MalformedParameterizedTypeException("Las ids seleccionadas no son validas para la busqueda masiva");
        return this.tidReactiveCassandraRepository.findAllById(ids);
    }
}
