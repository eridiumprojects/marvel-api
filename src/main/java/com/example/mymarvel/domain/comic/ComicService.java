package com.example.mymarvel.domain.comic;

import com.example.mymarvel.exceptions.ComicAlreadyExistException;
import com.example.mymarvel.exceptions.ComicNotFoundException;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterRepository;
import com.example.mymarvel.events.ComicSaveEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComicService {
    private final ComicRepository comicRepository;
    private final CharacterRepository characterRepository;
    private final ApplicationEventPublisher publisher;

    public Comic getComic(Long id) {
        return comicRepository.findById(id).orElseThrow(() -> new ComicNotFoundException("Not found..."));
    }

    public void save(Comic comic) {
        publisher.publishEvent(new ComicSaveEvent(comic));
        comicRepository.save(comic);
    }

    public List<Comic> getAll() {
        return comicRepository.findAll();
    }

    public List<Character> getCharacters(Long id) {
        return characterRepository.getCharactersByComicsId(getComic(id).getId());
    }

    public void isNameUnique(Comic comic) {
        Optional<Comic> comicOptional = comicRepository.findByName(comic.getName());
        if (comicOptional.isPresent()) {
            throw new ComicAlreadyExistException("Pososi");
        }
    }
}