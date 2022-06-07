package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.comic.Comic;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Component
public class CharacterMapper {
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
        if (characters == null){
            return Collections.emptyList();
        }
        return characters.stream().map(this::toView).toList();
    }

    public Character toCharacter(@Valid CharacterDto characterDto) {
        if (characterDto == null) {
            return null;
        }
        Character character =  new Character();
        character.setName(characterDto.getName());
        character.setComics(Collections.singletonList(new Comic().setId(characterDto.getComicId())));
        return character;
    }

}