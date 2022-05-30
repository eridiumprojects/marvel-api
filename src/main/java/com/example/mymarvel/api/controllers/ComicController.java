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
@RequestMapping("/v1/public/comic")
@RequiredArgsConstructor

public class ComicController {
    public final ComicService comicService;
    public final ComicMapper comicMapper;
    public final CharacterService characterService;
    public final CharacterMapper characterMapper;

    @GetMapping("/")
    public List<ComicDto> getAll() {
        List<Comic> allComic = comicService.getAll();
        return comicMapper.toDtos(allComic);
    }

    @GetMapping("/{comicId}")
    public ComicDto get(@PathVariable int comicId) {
        Comic comic = comicService.getComic((long) comicId);
        return comicMapper.toDto(comic);
    }

    @GetMapping("/{comicId}/character")
    public CharacterDto getCharacter(@PathVariable Long comicId) {
        Character character = characterService.getCharacter(comicId);
        return characterMapper.toDto(character);
    }
}
