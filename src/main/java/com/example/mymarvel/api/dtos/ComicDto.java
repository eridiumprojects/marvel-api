package com.example.mymarvel.api.dtos;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Validated
public class ComicDto {
    @NotEmpty
    private String name;
    @NotNull
    private List<String> characterNames;
}
