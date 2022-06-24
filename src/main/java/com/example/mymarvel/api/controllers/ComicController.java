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
import com.example.mymarvel.exceptions.ComicAlreadyExistException;
import com.example.mymarvel.exceptions.ComicNotFoundException;
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
@RequestMapping("/v1/public/comics")
@RequiredArgsConstructor
@Tag(name = "comic", description = "The comic API")
public class ComicController {
    public final ComicService comicService;
    public final ComicMapper comicMapper;
    public final CharacterService characterService;
    public final CharacterMapper characterMapper;
    public final CharacterRepository characterRepository;

    @Operation(summary = "Find all comics",
            description = "Find all comics from database",
            tags = "comic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Comic.class)))),
            @ApiResponse(responseCode = "404",
                    description = "Comics not found",
                    content = @Content(
                            schema = @Schema(implementation = ComicNotFoundException.class)))})

    @GetMapping(value = "/", produces = "application/json")
    public List<ComicView> getAll() {
        List<Comic> allComic = comicService.getAll();
        return comicMapper.toViews(allComic);
    }

    @Operation(summary = "Find comic by ID",
            description = "Find comic by ID from database",
            tags = "comic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Comic.class)))),
            @ApiResponse(responseCode = "404",
                    description = "Comic not found",
                    content = @Content(
                            schema = @Schema(implementation = ComicNotFoundException.class)))})
    @GetMapping(value = "/{comicId}", produces = "application/json")
    public ComicView getComic(@PathVariable Long comicId) {
        Comic comic = comicService.getComic(comicId);
        return comicMapper.toView(comic);
    }

    @Operation(summary = "Find a character with comic ID",
            description = "Find a character with comic ID from database",
            tags = {"character", "comic"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Comic.class)))),
            @ApiResponse(responseCode = "404",
                    description = "invalid comic ID",
                    content = @Content(
                            schema = @Schema(implementation = ComicNotFoundException.class)))})
    @GetMapping(value = "/{comicId}/characters", produces = "application/json")
    public List<CharacterView> getCharacters(@PathVariable Long comicId) {
        return characterMapper.toViews(characterRepository.getCharactersByComicsId(comicId));
    }

    @Operation(summary = "Save comic",
            description = "Save comic in database",
            tags = "comic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Comic.class)))),
            @ApiResponse(responseCode = "404",
                    description = "data entry error",
                    content = @Content(
                            schema = @Schema(implementation = ComicAlreadyExistException.class)))})
    @PostMapping(value = "", consumes = "application/json")
    public ComicView saveComic(@Valid @RequestBody ComicDto comicDto) {
        return comicMapper.toView(comicService.save(comicMapper.toComic(comicDto)));
    }

    @Operation(summary = "Delete comic by ID",
            description = "Delete comic from database",
            tags = "comic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Comic.class)))),
            @ApiResponse(responseCode = "404",
                    description = "invalid comic ID",
                    content = @Content(
                            schema = @Schema(implementation = ComicNotFoundException.class)))})

    @DeleteMapping(value = "/delete/{comicId}", produces = "application/json")
    public void deleteComic(@PathVariable Long comicId) {
        comicService.delete(comicService.getComic(comicId));
    }

    @Operation(summary = "Update comic",
            description = "Update comic from database",
            tags = "comic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Comic.class)))),
            @ApiResponse(responseCode = "404",
                    description = "invalid comic ID",
                    content = @Content(
                            schema = @Schema(implementation = ComicNotFoundException.class)))})
    @PutMapping(value = "/update/{comicId}", produces = "application/json")
    public void updateComic(@PathVariable Long comicId, @Valid @RequestBody UpdatedComic updatedComic) {
        comicService.update(comicId, updatedComic);
    }
}