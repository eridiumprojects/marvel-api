package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicRepository;
import com.example.mymarvel.exceptions.ComicNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CharacterMapper {
    @Autowired
    public ComicRepository comicRepository;

    public CharacterView toView(Character character) {
        if (character == null) {
            return null;
        }
        CharacterView characterView = new CharacterView();
        characterView.setId(character.getId());
        characterView.setName(character.getName());
        return characterView;
    }

    public List<CharacterView> toViews(List<Character> characters) {
        if (characters == null) {
            return Collections.emptyList();
        }
        return characters.stream().map(this::toView).toList();
    }

    public Character toCharacter(@Valid CharacterDto characterDto) {
        if (characterDto == null) {
            return null;
        }
        Character character = new Character();
        character.setName(characterDto.getName());
        List<Comic> list = new ArrayList<>();
        int count = characterDto.getComicId().size();
        for (int i = 0; i < count; i++) {
            list.add(comicRepository.findById(characterDto.getComicId().get(i)).
                    orElseThrow(() -> new ComicNotFoundException("The ID with such a character was not found")));
        }
        character.setComics(list);
        return character;
    }

}