package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.mappers.CharacterMapper;
import com.example.mymarvel.api.mappers.ComicMapper;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterService;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/public/character")
@RequiredArgsConstructor
public class CharacterController {

    public final CharacterService characterService;
    public final CharacterMapper characterMapper;
    public final ComicMapper comicMapper;

    public final ComicService comicService;

    @GetMapping("/")
    public List<CharacterDto> getAll() {
        List<Character> allCharacters = characterService.getAll();
        return characterMapper.toDtos(allCharacters);
    }

    @GetMapping("/{characterId}")
    public CharacterDto get(@PathVariable Long characterId) {
        Character character = characterService.getCharacter(characterId);
        return characterMapper.toDto(character);
    }

    @GetMapping("/{characterId}/comic")
    public ComicDto getComic(@PathVariable Long characterId) {
        Comic comic = comicService.getComic(characterId);
        return comicMapper.toDto(comic);
    }

}
