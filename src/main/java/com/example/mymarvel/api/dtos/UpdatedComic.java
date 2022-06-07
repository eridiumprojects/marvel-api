package com.example.mymarvel.api.dtos;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class UpdatedComic {
    @NotNull
    private Long id;
    @NotEmpty
    private String newName;

}
