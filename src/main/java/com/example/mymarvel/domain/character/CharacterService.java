package com.example.mymarvel.domain.character;

import com.example.mymarvel.exceptions.CharacterNotFoundException;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicRepository;
import com.example.mymarvel.domain.comic.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final ComicService comicService;
    public Character getCharacter(Long id)  {
        return characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException("Not found..."));
    }

    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    public void save(Character character) {
        Comic comic = comicService.getComic(character.getComics().get(0).getId());
        character.setComics(Collections.singletonList(comic));
        characterRepository.save(character);
    }

}