package com.example.mymarvel.api.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
@Data
@Validated
public class CharacterDto {
    private Long id;
    @NotEmpty
    private String name;

}
