package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.dtos.ComicView;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.comic.Comic;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
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


    public ComicView toView(ComicDto comicDto) {
        if (comicDto == null) {
            return null;
        }
        ComicView comicView = new ComicView();
        comicView.setId(comicDto.getId());
        comicView.setName(comicDto.getName());
        return comicView;
    }

    public List<ComicView> toViews(List<ComicDto> comicsDto) {
        return comicsDto.stream().map(this::toView).toList();
    }

    public Comic toComic(@Valid ComicDto comicDto) {
        if (comicDto == null) {
            return null;
        }
        Comic comic = new Comic();
        comic.setId(comicDto.getId());
        comic.setName(comicDto.getName());
        return comic;
    }

}