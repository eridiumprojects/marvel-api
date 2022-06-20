package com.example.mymarvel.init;

import com.example.mymarvel.api.controllers.ComicController;
import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterRepository;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicRepository;
import com.example.mymarvel.exceptions.ComicNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class DbInit {
    Long id;
    List<Character> characters;
    @Autowired
    private ComicController comicController;
    @Autowired
    private ComicRepository comicRepository;
    @Autowired
    private CharacterRepository characterRepository;

    @PostConstruct
    private void postInit() {
        ComicDto comicDto = new ComicDto();
        comicDto.setName("Hagwarts");
        comicDto.setCharacterNames(List.of("Ran", "Germion"));
        comicController.saveComic(comicDto);
        Comic comic = comicRepository.findByName(comicDto.getName()).orElseThrow(() ->
                new ComicNotFoundException("Not found..."));
        id = comic.getId();
        characters = characterRepository.getCharactersByComicsId(comic.getId());
    }

    @PreDestroy
    private void preDestroy() {
        comicController.deleteComic(id);
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getComics().size() == 1) {
                characterRepository.deleteById(characters.get(i).getId());
            }
        }
    }

}