package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.domain.character.Character;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
public class CharacterMapper {
    public CharacterDto toDto(Character character) {
        if (character == null) {
            return null;
        }

        CharacterDto characterDto = new CharacterDto();
        characterDto.setId(character.getId());
        characterDto.setName(character.getName());
        return characterDto;
    }

    public List<CharacterDto> toDtos(List<Character> characters) {
        return characters.stream().map(this::toDto).toList();
    }

    public CharacterView toView(CharacterDto characterDto) {
        if (characterDto == null) {
            return null;
        }
        CharacterView characterView = new CharacterView();
        characterView.setId(characterDto.getId());
        characterView.setName(characterDto.getName());
        return characterView;
    }

    public List<CharacterView> toViews(List<CharacterDto> charactersDto) {
        return charactersDto.stream().map(this::toView).toList();
    }

    public Character toCharacter(@Valid CharacterDto characterDto) {
        if (characterDto == null) {
            return null;
        }
        Character character = new Character();
        character.setId(characterDto.getId());
        character.setName(characterDto.getName());
        return character;
    }

}