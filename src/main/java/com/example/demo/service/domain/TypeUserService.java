package com.example.demo.service.domain;

import com.example.demo.model.domain.TypeUserModel;
import com.example.demo.service.base.BaseService;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface TypeUserService extends BaseService<TypeUserModel, UUID> {

    Mono<TypeUserModel> findByName(String name);
}
