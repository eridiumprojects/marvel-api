package com.example.mymarvel.api.dtos;

import lombok.Data;

@Data //аннотация активирует сразу сеттеры, геттеры и конструкторы
public class ComicDto {
    private Long id;
    private String name;
}
