package com.example.mymarvel.api.dtos;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Validated
public class UpdatedCharacter {
    @NotNull
    List<Long> comicsId = new ArrayList<>();
}
