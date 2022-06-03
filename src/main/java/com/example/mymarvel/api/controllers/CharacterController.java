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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/public/characters")
@RequiredArgsConstructor
public class CharacterController {
    @Autowired
    public final CharacterService characterService;
    public final CharacterMapper characterMapper;
    public final ComicMapper comicMapper;
    public final ComicService comicService;

    @GetMapping(value = "/", produces = "application/json")
    public List<CharacterView> getAll() {
        List<Character> allCharacters = characterService.getAll();
        List<CharacterDto> allCharactersDto = characterMapper.toDtos(allCharacters);
        return characterMapper.toViews(allCharactersDto);
    }

    @GetMapping(value = "/{characterId}", produces = "application/json")
    public CharacterView getCharacter(@PathVariable Long characterId) {
        Character character = characterService.getCharacter(characterId);
        CharacterDto characterDto = characterMapper.toDto(character);
        return characterMapper.toView(characterDto);
    }

    @GetMapping(value = "/{characterId}/comics", produces = "application/json")
    public List<ComicView> getComics(@PathVariable Long characterId) {
        List<Comic> comics = characterService.getComics(characterId);
        List<ComicDto> comicsDto = comicMapper.toDtos(comics);
        return comicMapper.toViews(comicsDto);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public void saveCharacter(@Valid @RequestBody CharacterDto characterDto) {
        Character character = characterMapper.toCharacter(characterDto);
        characterService.save(character);
    }

}