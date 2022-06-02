package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.exceptions.ComicNotFoundException;
import com.example.mymarvel.api.mappers.CharacterMapper;
import com.example.mymarvel.api.mappers.ComicMapper;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterService;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<ComicDto> getAll() {
        List<Comic> allComic = comicService.getAll();
        return comicMapper.toDtos(allComic);
    }

    @GetMapping(value = "/{comicId}", produces = "application/json")
    public ComicDto getComic(@PathVariable Long comicId) {
        Comic comic = comicService.getComic(comicId);
        return comicMapper.toDto(comic);
    }

    @GetMapping(value = "/{comicId}/characters", produces = "application/json")
    public List<CharacterDto> getCharacters(@PathVariable Long comicId){
        return characterMapper.toDtos(comicService.getCharacters(comicId));
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Comic> saveComic(@RequestBody Comic comic) {
        if (comic == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.comicService.save(comic);
        return new ResponseEntity<>(comic,HttpStatus.OK);
    }
}
