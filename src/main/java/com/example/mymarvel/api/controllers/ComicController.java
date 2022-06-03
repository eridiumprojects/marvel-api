package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.dtos.ComicView;
import com.example.mymarvel.api.mappers.CharacterMapper;
import com.example.mymarvel.api.mappers.ComicMapper;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterService;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/public/comics")
@RequiredArgsConstructor
public class ComicController {
    public final ComicService comicService;
    public final ComicMapper comicMapper;
    public final CharacterService characterService;
    public final CharacterMapper characterMapper;
    @GetMapping(value = "/", produces = "application/json")
    public List<ComicView> getAll() {
        List<Comic> allComic = comicService.getAll();
        List<ComicDto> allComicDto = comicMapper.toDtos(allComic);
        return comicMapper.toViews(allComicDto);
    }

    @GetMapping(value = "/{comicId}", produces = "application/json")
    public ComicView getComic(@PathVariable Long comicId) {
        Comic comic = comicService.getComic(comicId);
        ComicDto comicDto = comicMapper.toDto(comic);
        return comicMapper.toView(comicDto);
    }

    @GetMapping(value = "/{comicId}/characters", produces = "application/json")
    public List<CharacterView> getCharacters(@PathVariable Long comicId){
        List<Character> characters = comicService.getCharacters(comicId);
        List<CharacterDto> charactersDto = characterMapper.toDtos(characters);
        return characterMapper.toViews(charactersDto);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public void saveComic(@Valid @RequestBody ComicDto comicDto) {
        comicService.save(comicMapper.toComic(comicDto));
    }
}