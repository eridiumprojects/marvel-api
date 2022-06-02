package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.domain.character.Character;
import org.springframework.stereotype.Component;

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

}
