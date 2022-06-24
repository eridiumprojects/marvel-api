package com.example.mymarvel.api.controllers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.api.dtos.ComicView;
import com.example.mymarvel.api.dtos.UpdatedCharacter;
import com.example.mymarvel.api.mappers.CharacterMapper;
import com.example.mymarvel.api.mappers.ComicMapper;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterRepository;
import com.example.mymarvel.domain.character.CharacterService;
import com.example.mymarvel.domain.comic.ComicRepository;
import com.example.mymarvel.domain.comic.ComicService;
import com.example.mymarvel.exceptions.CharacterAlreadyExistException;
import com.example.mymarvel.exceptions.CharacterNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/public/characters")
@RequiredArgsConstructor
@Tag(name = "character", description = "The character API")
public class CharacterController {
    public final CharacterService characterService;
    public final CharacterMapper characterMapper;
    public final CharacterRepository characterRepository;


    public final ComicRepository comicRepository;
    public final ComicMapper comicMapper;
    public final ComicService comicService;

    @Operation(summary = "Find all characters",
            description = "Find all characters from database",
            tags = "character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Character.class)))),
            @ApiResponse(responseCode = "404",
                    description = "Characters not found",
                    content = @Content(
                            schema = @Schema(implementation = CharacterNotFoundException.class)))})

    @GetMapping(value = "/", produces = "application/json")
    public List<CharacterView> getAll() {
        List<Character> allCharacters = characterService.getAll();
        return characterMapper.toViews(allCharacters);
    }

    @Operation(summary = "Find character by ID",
            description = "Find character by ID from database",
            tags = "character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Character.class)))),
            @ApiResponse(responseCode = "404",
                    description = "Character not found",
                    content = @Content(
                            schema = @Schema(implementation = CharacterNotFoundException.class)))})
    @GetMapping(value = "/{characterId}", produces = "application/json")
    public CharacterView getCharacter(@PathVariable Long characterId) {
        return characterMapper.toView(characterService.getCharacter(characterId));
    }

    @Operation(summary = "Find a comic with a character ID",
            description = "Find a comic with a character ID from database",
            tags = {"character", "comic"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Character.class)))),
            @ApiResponse(responseCode = "404",
                    description = "invalid character ID",
                    content = @Content(
                            schema = @Schema(implementation = CharacterNotFoundException.class)))})

    @GetMapping(value = "/{characterId}/comics", produces = "application/json")

    public List<ComicView> getComics(@PathVariable Long characterId) {
        return comicMapper.toViews(comicRepository.getComicsByCharacterId(characterId));
    }

    @Operation(summary = "Save character",
            description = "Save character in database",
            tags = "character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Character.class)))),
            @ApiResponse(responseCode = "404",
                    description = "data entry error",
                    content = @Content(
                            schema = @Schema(implementation = CharacterAlreadyExistException.class)))})
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")

    public CharacterView saveCharacter(@Valid @RequestBody CharacterDto characterDto) {
        return characterMapper.toView(characterService.save(characterMapper.toCharacter(characterDto)));
    }


    @Operation(summary = "Delete character by ID",
            description = "Delete character from database",
            tags = "character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Character.class)))),
            @ApiResponse(responseCode = "404",
                    description = "invalid character ID",
                    content = @Content(
                            schema = @Schema(implementation = CharacterNotFoundException.class)))})

    @DeleteMapping(value = "/delete/{characterId}", produces = "application/json")
    public void deleteCharacter(@PathVariable Long characterId) {
        characterService.delete(characterService.getCharacter(characterId));
    }

    @Operation(summary = "Update character",
            description = "Update character from database",
            tags = "character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Character.class)))),
            @ApiResponse(responseCode = "404",
                    description = "invalid character ID",
                    content = @Content(
                            schema = @Schema(implementation = CharacterNotFoundException.class)))})
    @PutMapping(value = "/update/{characterId}", produces = "application/json")
    public void updateCharacter(@PathVariable Long characterId, @Valid @RequestBody UpdatedCharacter updatedCharacter) {
        characterService.update(characterId, updatedCharacter);
    }

}