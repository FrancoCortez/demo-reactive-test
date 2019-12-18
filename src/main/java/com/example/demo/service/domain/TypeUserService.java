package com.example.demo.service.domain;

import com.example.demo.model.TypeUserModel;
import com.example.demo.service.base.BaseService;
import reactor.core.publisher.Mono;

public interface TypeUserService extends BaseService<TypeUserModel, String> {

    Mono<TypeUserModel> findByName(String name);
}
