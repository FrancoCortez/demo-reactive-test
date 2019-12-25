package com.example.demo.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseIdModel {
    @PrimaryKey
    private UUID id;
    @CreatedBy
    @Column("create_by")
    private String createBy;
    @CreatedDate
    @Column("create_date")
    private LocalDateTime createdDate;
    @LastModifiedBy
    @Column("last_modified_by")
    private String lastModifiedBy;
    @LastModifiedDate
    @Column("last_modified_date")
    private LocalDateTime lastModifiedDate;
}
