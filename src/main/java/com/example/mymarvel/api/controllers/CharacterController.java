package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.*;
import com.example.mymarvel.api.mappers.CharacterMapper;
import com.example.mymarvel.api.mappers.ComicMapper;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterRepository;
import com.example.mymarvel.domain.character.CharacterService;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicRepository;
import com.example.mymarvel.domain.comic.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/public/characters")
@RequiredArgsConstructor
public class CharacterController {
    public final CharacterService characterService;
    public final CharacterMapper characterMapper;
    public final CharacterRepository characterRepository;
    public final ComicRepository comicRepository;
    public final ComicMapper comicMapper;
    public final ComicService comicService;

    @GetMapping(value = "/", produces = "application/json")
    public List<CharacterView> getAll() {
        List<Character> allCharacters = characterService.getAll();
        return characterMapper.toViews(allCharacters);
    }

    @GetMapping(value = "/{characterId}", produces = "application/json")
    public CharacterView getCharacter(@PathVariable Long characterId) {
        return characterMapper.toView(characterService.getCharacter(characterId));
    }

    @GetMapping(value = "/{characterId}/comics", produces = "application/json")
    public List<ComicView> getComics(@PathVariable Long characterId) {
        return comicMapper.toViews(comicRepository.getComicsByCharacterId(characterId));
    }

    @PostMapping(value = "", consumes = "application/json")
    public void saveCharacter(@Valid @RequestBody CharacterDto characterDto) {
        characterService.save(characterMapper.toCharacter(characterDto));
    }

    @DeleteMapping(value = "/delete/{characterId}", produces = "application/json")
    public void deleteCharacter(@PathVariable Long characterId) {
        characterService.delete(characterService.getCharacter(characterId));
    }

    @PutMapping(value = "/update", produces = "application/json")
    public void updateCharacter(@Valid @RequestBody UpdatedCharacter updatedCharacter) {
        characterService.update(updatedCharacter);

    }

}