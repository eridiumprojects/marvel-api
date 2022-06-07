package com.example.mymarvel.api.dtos;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@Validated
public class CharacterDto {
    @NotEmpty
    private String name;
    @Positive
    private Long comicId;
}
