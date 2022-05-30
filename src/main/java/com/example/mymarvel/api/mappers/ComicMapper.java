package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.domain.comic.Comic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComicMapper {
    public ComicDto toDto(Comic comic) {
        if (comic == null) {
            return null;
        }

        ComicDto comicDto = new ComicDto();
        comicDto.setId(comic.getId());
        comicDto.setName(comic.getName());
        return comicDto;
    }

    public List<ComicDto> toDtos(List<Comic> comic) {
        return comic.stream().map(this::toDto).toList();
    }
}
