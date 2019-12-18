package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import com.example.demo.model.base.BaseIdModel;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("typeUser")
public class TypeUserModel extends BaseIdModel {
    @NotNull
    private String name;
    private String description;
}
