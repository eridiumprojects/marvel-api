package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.domain.character.Character;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
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
        return characters.stream().map(this::toView).toList();
    }

    public Character toCharacter(@Valid CharacterDto characterDto) {
        if (characterDto == null) {
            return null;
        }
        Character character =  new Character();
        character.setId(characterDto.getId());
        character.setName(characterDto.getName());
        return character;
    }

}