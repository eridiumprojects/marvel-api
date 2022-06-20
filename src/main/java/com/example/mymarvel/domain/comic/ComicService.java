package com.example.mymarvel.domain.comic;

import com.example.mymarvel.api.dtos.UpdatedComic;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterRepository;
import com.example.mymarvel.events.ComicSaveEvent;
import com.example.mymarvel.exceptions.CharacterAlreadyExistException;
import com.example.mymarvel.exceptions.CharacterNotFoundException;
import com.example.mymarvel.exceptions.ComicAlreadyExistException;
import com.example.mymarvel.exceptions.ComicNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComicService {
    private final ComicRepository comicRepository;
    private final CharacterRepository characterRepository;
    private final ApplicationEventPublisher publisher;


    @Transactional
    public Comic getComic(Long id) {
        return comicRepository.findById(id).orElseThrow(() -> new ComicNotFoundException("The ID with such a comic was not found"));
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Comic save(Comic comic) {
        publisher.publishEvent(new ComicSaveEvent(comic));
        isNameUnique(comic.getName());
        isCharactersUnique(comic.getCharacters());
        return comicRepository.save(comic);
    }

    @Transactional
    public List<Comic> getAll() {
        return comicRepository.findAll();
    }

    public void isNameUnique(String name) {
        Optional<Comic> comicOptional = comicRepository.findByName(name);
        if (comicOptional.isPresent()) {
            throw new ComicAlreadyExistException("A comic with that name already exists");
        }
    }

    @Transactional
    public void isCharactersUnique(List<Character> characters) {
        for (int i = 0; i < characters.size(); i++) {
            Optional<Character> characterOptional = characterRepository.findByName(characters.get(i).getName());
            if (characterOptional.isPresent()) {
                throw new CharacterAlreadyExistException("A comic with that name already exists");
            }
        }
    }

    @Transactional
    public void delete(Comic comic) {
        comicRepository.delete(comic);
    }

    @Transactional
    public void update(Long comicId, UpdatedComic updatedComic) {
        Comic comic = comicRepository.
                findById(comicId).
                orElseThrow(() -> new ComicNotFoundException("The ID with such a comic was not found"));
        List<String> listOfString = updatedComic.getCharacterNames();
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < listOfString.size(); i++) {
            characters.add(characterRepository.findByName(listOfString.get(i)).
                    orElseThrow(() -> new CharacterNotFoundException("The ID with such a comic was not found")));
        }
        comic.setCharacters(characters);
        comicRepository.save(comic);
    }
}
