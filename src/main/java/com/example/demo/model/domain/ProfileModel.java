package com.example.demo.model.domain;

import com.example.demo.model.base.BaseIdModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("profile")
public class ProfileModel extends BaseIdModel {
    @NotNull
    @Indexed
    private String name;
    private String description;
}
