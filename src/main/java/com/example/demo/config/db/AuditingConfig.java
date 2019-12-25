package com.example.demo.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.EnableCassandraAuditing;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
@EnableCassandraAuditing
@EnableReactiveCassandraRepositories(basePackages = "com.example.demo.repository.*")
public class AuditingConfig {
    @Bean
    public AuditorAware<String> myAuditorProvider() {
        return () -> Optional.of("LEGACY");
    }
}
