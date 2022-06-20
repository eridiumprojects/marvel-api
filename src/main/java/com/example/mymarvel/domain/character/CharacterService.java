package com.example.mymarvel.domain.character;

import com.example.mymarvel.api.dtos.UpdatedCharacter;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicRepository;
import com.example.mymarvel.domain.comic.ComicService;
import com.example.mymarvel.exceptions.CharacterAlreadyExistException;
import com.example.mymarvel.exceptions.CharacterNotFoundException;
import com.example.mymarvel.exceptions.ComicNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final ComicRepository comicRepository;
    private final ComicService comicService;

    @Transactional
    public Character getCharacter(Long id) {
        return characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException("The ID with such a character was not found"));
    }

    @Transactional
    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Character save(Character character) {
        isNameUnique(character.getName());
        characterRepository.save(character);
        return character;
    }

    @Transactional
    public void isNameUnique(String name) {
        Optional<Character> characterOptional = characterRepository.findByName(name);

        if (characterOptional.isPresent()) {
            throw new CharacterAlreadyExistException("A character with that name already exists");
        }
    }

    @Transactional
    public void delete(Character character) {
        characterRepository.findById(character.getId()).
                orElseThrow(() -> new CharacterNotFoundException("The ID with such a character was not found"));
        characterRepository.delete(character);
    }

    @Transactional
    public void update(Long characterId, UpdatedCharacter updatedCharacter) {
        List<Long> listId = updatedCharacter.getComicsId();
        Character character = characterRepository.findById(characterId).
                orElseThrow(() -> new CharacterNotFoundException("The ID with such a character was not found"));
        List<Comic> comicList = new ArrayList<>();
        for (int i = 0; i < listId.size(); i++) {
            comicList.add(comicRepository.findById(listId.get(i)).
                    orElseThrow(() -> new ComicNotFoundException("The ID with such a character was not found")));
        }
        character.setComics(comicList);
        characterRepository.save(character);

    }
}

