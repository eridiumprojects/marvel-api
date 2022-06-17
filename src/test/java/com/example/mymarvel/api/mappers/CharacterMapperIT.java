package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.domain.character.Character;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharacterMapperIT {

  private CharacterMapper characterMapper =  new CharacterMapper();

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void toView() {
        Character character = new Character();
        character.setId(1L);
        character.setName("Aboba");
        CharacterView characterView = characterMapper.toView(character);
        assertEquals(characterView.getName(),character.getName());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void toViews() {
        List<Character> characters = new ArrayList<>();
        Character character1 = new Character();
        character1.setName("Aboba");
        character1.setId(1L);
        characters.add(character1);
        List<CharacterView> characterViews = characterMapper.toViews(characters);
        assertEquals(characterViews.get(0).getName(),(characters.get(0).getName()));

    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void toCharacter() {
        CharacterDto characterDto = new CharacterDto();
        characterDto.setName("Abobaa");
        characterDto.setComicId(1L);
        Character character = characterMapper.toCharacter(characterDto);
        assertEquals(character.getName(),characterDto.getName());

    }
}