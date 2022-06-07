package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.dtos.ComicView;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.comic.Comic;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
public class ComicMapper {

    public ComicView toView(Comic comic) {
        if (comic == null) {
            return null;
        }
        ComicView comicView = new ComicView();
        comicView.setId(comic.getId());
        comicView.setName(comic.getName());
        return comicView;
    }

    public List<ComicView> toViews(List<Comic> comics) {
        return comics.stream().map(this::toView).toList();
    }

    public Comic toComic(@Valid ComicDto comicDto) {
        if (comicDto == null) {
            return null;
        }
        Comic comic = new Comic();
        comic.setName(comicDto.getName());
        comic.setCharacters(getCharacters(comicDto));
        return comic;
    }

    private List<Character> getCharacters(ComicDto comicDto) {
        return comicDto.getCharacterNames().stream().map(this::getCharacter).toList();
    }

    private Character getCharacter(String s) {
        return new Character().setName(s);
    }

}