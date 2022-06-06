package com.example.mymarvel.api.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
public class ComicDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private List<String> comicNames;
}
