package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.dtos.ComicView;
import com.example.mymarvel.api.dtos.UpdatedComic;
import com.example.mymarvel.api.mappers.CharacterMapper;
import com.example.mymarvel.api.mappers.ComicMapper;
import com.example.mymarvel.domain.character.CharacterRepository;
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
    public final CharacterRepository characterRepository;

    @GetMapping(value = "/", produces = "application/json")
    public List<ComicView> getAll() {
        List<Comic> allComic = comicService.getAll();
        return comicMapper.toViews(allComic);
    }

    @GetMapping(value = "/{comicId}", produces = "application/json")
    public ComicView getComic(@PathVariable Long comicId) {
        Comic comic = comicService.getComic(comicId);
        return comicMapper.toView(comic);
    }

    @GetMapping(value = "/{comicId}/characters", produces = "application/json")
    public List<CharacterView> getCharacters(@PathVariable Long comicId){
        return characterMapper.toViews(characterRepository.getCharactersByComicsId(comicId));
    }

    @PostMapping(value = "", consumes = "application/json")
    public void saveComic(@Valid @RequestBody ComicDto comicDto) {
        comicService.save(comicMapper.toComic(comicDto));
    }

    @DeleteMapping(value = "/delete/{comicId}", produces = "application/json")
    public void deleteComic(@PathVariable Long comicId) {
        comicService.delete(comicService.getComic(comicId));
    }

    @PutMapping(value = "/{}/update", produces = "application/json")
    public void updateComic(@Valid @RequestBody UpdatedComic updatedComic) {
        comicService.update(updatedComic);
    }
}