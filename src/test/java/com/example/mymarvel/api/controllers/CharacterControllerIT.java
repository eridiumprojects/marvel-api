package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.UpdatedCharacter;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterRepository;
import com.example.mymarvel.domain.character.CharacterService;
import com.example.mymarvel.exceptions.CharacterNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class CharacterControllerIT {
    @Autowired
    private CharacterController characterController;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    public CharacterService characterService;


    public CharacterControllerIT() {
    }

    @Test
    void getAllCharacters(){
        assertTrue(characterController.getAll().size() > 0);

    }

    @Test
    void getCharacter() {
        assertEquals(15L, characterController.getCharacter(15L).getId());
    }

    @Test
    void getComics() {
        assertTrue(characterController.getComics(28L).size() != 0);
    }

    @Test
    void saveCharacter() {
        CharacterDto characterDto = new CharacterDto();
        characterDto.setName("Aboba");
        characterDto.setComicId(7L);
        characterController.saveCharacter(characterDto);
        Character character = characterRepository.findByName("Aboba").
                orElseThrow(() -> new CharacterNotFoundException("Can't find character..."));
        assertEquals("Aboba", character.getName());
    }

    @Test
    void deleteCharacter() {
        boolean flag;
        characterController.deleteCharacter(29L);
        flag = true;
        assertTrue(flag);
    }

    @Test
    void updateCharacter() {
        UpdatedCharacter updatedCharacter = new UpdatedCharacter();
        updatedCharacter.setId(28L);
        updatedCharacter.setNewName("Papa");
        characterController.updateCharacter(updatedCharacter);
        Character character = characterRepository.findByName(updatedCharacter.getNewName()).
                orElseThrow(() -> new CharacterNotFoundException("Not found..."));
        assertEquals(character.getName(),updatedCharacter.getNewName());
    }
}
