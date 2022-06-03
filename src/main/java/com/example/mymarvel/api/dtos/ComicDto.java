package com.example.mymarvel.api.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
@Data//аннотация активирует сразу сеттеры, геттеры и конструкторы
@Validated
public class ComicDto {
    private Long id;
    @NotEmpty
    private String name;
}
