package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.exceptions.CharacterNotFoundException;
import com.example.mymarvel.api.mappers.CharacterMapper;
import com.example.mymarvel.api.mappers.ComicMapper;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterService;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/",produces = "application/json")
    public List<CharacterDto> getAll() {
        List<Character> allCharacters = characterService.getAll();
        return characterMapper.toDtos(allCharacters);
    }

    @GetMapping(value = "/{characterId}", produces = "application/json")
    public CharacterDto getCharacter(@PathVariable Long characterId) {
        Character character = characterService.getCharacter(characterId);
        return characterMapper.toDto(character);
    }

    @GetMapping(value = "/{characterId}/comics", produces = "application/json")
    public List<ComicDto> getComics(@PathVariable Long characterId) {
        return comicMapper.toDtos(characterService.getComics(characterId));
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    //Дописать проверку имени на валидацию (допустимость использования только цифр)
    //если встречаются буквы, кинуть Unprocessable entity 422 exception
    public void saveCharacter(@RequestBody Character character) {
        if (character == null) {
            throw new CharacterNotFoundException();
        }
        characterService.save(character);
    }

}
