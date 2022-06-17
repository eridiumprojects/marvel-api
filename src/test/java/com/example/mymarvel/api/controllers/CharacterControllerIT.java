package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.api.dtos.UpdatedCharacter;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterRepository;
import com.example.mymarvel.domain.character.CharacterService;
import com.example.mymarvel.exceptions.CharacterNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CharacterControllerIT {

    @Autowired
    private CharacterController characterController;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    public CharacterService characterService;


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void getAllCharacters() {
        assertTrue(characterController.getAll().size() > 0);
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void getCharacter() {
        assertEquals(1L, characterController.getCharacter(1L).getId());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void getComics() {
        assertTrue(characterController.getComics(1L).size() != 0);
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void saveCharacter() {
        CharacterDto characterDto = new CharacterDto();
        characterDto.setName("Aboba");
        CharacterView characterView = characterController.saveCharacter(characterDto);
        characterDto.setComicId(characterView.getId());
        Character character = characterRepository.findByName(characterDto.getName()).
                orElseThrow(() -> new CharacterNotFoundException("Can't find character..."));
        assertEquals(characterDto.getName(), character.getName());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void deleteCharacter() {
        boolean flag;
        characterController.deleteCharacter(1L);
        flag = true;
        assertTrue(flag);
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void updateCharacter() {
        UpdatedCharacter updatedCharacter = new UpdatedCharacter();
        updatedCharacter.setId(1L);
        updatedCharacter.setNewName("Papa");
        characterController.updateCharacter(updatedCharacter);
        Character character = characterRepository.findByName(updatedCharacter.getNewName()).
                orElseThrow(() -> new CharacterNotFoundException("Not found..."));
        assertEquals(character.getName(), updatedCharacter.getNewName());
    }
}
