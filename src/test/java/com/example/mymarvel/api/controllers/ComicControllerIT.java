package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.dtos.UpdatedComic;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicRepository;
import com.example.mymarvel.exceptions.ComicNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional

class ComicControllerIT {

    @Autowired
    private ComicController comicController;
    @Autowired
    private CharacterController characterController;
    @Autowired
    private ComicRepository comicRepository;

    public ComicControllerIT() {

    }
    @Test
    void getAllComics() {
        boolean flag = comicController.getAll().size() > 0;
        assertTrue(flag);
    }

    @Test
    void getComic() {
        assertEquals(7L, comicController.getComic(7L).getId());
    }

    @Test
    void getCharacters() {
        boolean flag = comicController.getCharacters(7L).size() != 0;
        assertTrue(flag);

    }

    @Test
    void saveComic() {
        ComicDto comicDto = new ComicDto();
        comicDto.setName("Marrrvel");
        List<String> characterNames = new ArrayList<>();
        characterNames.add("Aboba");
        comicDto.setCharacterNames(characterNames);
        comicController.saveComic(comicDto);
        Comic comic = comicRepository.findByName("Marrrvel").
                orElseThrow(() -> new ComicNotFoundException("Comic not found..."));
        assertEquals("Marrrvel",comic.getName());

    }

    @Test
    void deleteComic() {
        boolean flag;
        comicController.deleteComic(7L);
        flag = true;
        assertTrue(flag);
    }

    @Test
    void updateComic() {
        UpdatedComic updatedComic = new UpdatedComic();
        updatedComic.setId(7L);
        updatedComic.setNewName("Marrrvel");
        comicController.updateComic(updatedComic);
        Comic comic = comicRepository.findByName(updatedComic.getNewName()).
                orElseThrow(() -> new ComicNotFoundException("Not found..."));
        assertEquals(comic.getName(),updatedComic.getNewName());
    }
}