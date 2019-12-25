package com.example.demo.service.domain;

import com.example.demo.model.domain.ProfileModel;
import com.example.demo.service.base.BaseService;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProfileService extends BaseService<ProfileModel, UUID> {

    Mono<ProfileModel> findByName(String name);
}
