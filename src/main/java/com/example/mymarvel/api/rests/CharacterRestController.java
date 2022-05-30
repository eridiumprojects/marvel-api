package com.example.mymarvel.api.rests;


import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/public/character")
@RequiredArgsConstructor
public class CharacterRestController {
    @Autowired
    private final CharacterService characterService;
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> getCharacter(@PathVariable("id") Long characterId) {
        if (characterId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Character character = this.characterService.getCharacter(characterId);

        if (character == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(character,HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> saveCharacter(@RequestBody Character character) {
        if (character == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.characterService.save(character);

        return new ResponseEntity<>(character,HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> updateCharacter(@RequestBody Character character) {
        if (character == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.characterService.save(character);
        return new ResponseEntity<>(character, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> deleteCharacter(@PathVariable("id") Long id) {

        Character character = this.characterService.getCharacter(id);

        if (character == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.characterService.delete(character);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Character>> getAll() {
        List<Character> characters = this.characterService.getAll();
        if (characters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(characters,HttpStatus.OK);

        }
    }

