package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.dtos.UpdatedComic;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterRepository;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicRepository;
import com.example.mymarvel.exceptions.CharacterNotFoundException;
import com.example.mymarvel.exceptions.ComicNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class
ComicControllerIT {

    @Autowired
    private ComicController comicController;
    @Autowired
    private CharacterController characterController;
    @Autowired
    private ComicRepository comicRepository;
    @Autowired
    private CharacterRepository characterRepository;


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void getAllComics() {
        boolean flag = comicController.getAll().size() > 0;
        assertTrue(flag);
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void getCharacters() {
        List<CharacterView> characterViews = comicController.getCharacters(1L);
        assertTrue(characterViews.size() > 0);

    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void getComic() {
        assertEquals(1L, comicController.getComic(1L).getId());
    }


    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void saveComic() {
        ComicDto comicDto = new ComicDto();
        comicDto.setName("Marrrvel");
        List<String> characterNames = new ArrayList<>();
        characterNames.add("Aboba");
        comicDto.setCharacterNames(characterNames);
        comicController.saveComic(comicDto);
        Comic comic = comicRepository.findByName(comicDto.getName()).
                orElseThrow(() -> new ComicNotFoundException("Comic not found..."));
        assertEquals(comicDto.getName(), comic.getName());

    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void deleteComic() {
        comicController.deleteComic(4L);
        assertThrows(ComicNotFoundException.class, () -> comicController.getComic(4L));
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void updateComic() {
        UpdatedComic updatedComic = new UpdatedComic();
        updatedComic.setCharacterNames(List.of("Spider-man","Batman"));
        Long comicId = 1L;
        comicController.updateComic(comicId,updatedComic);
        Comic comic = comicRepository.findById(comicId).
                orElseThrow(() -> new ComicNotFoundException("Not found..."));
        assertEquals(updatedComic.getCharacterNames().size(),comic.getCharacters().size());
    }
}