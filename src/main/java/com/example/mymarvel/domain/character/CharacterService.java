package com.example.mymarvel.domain.character;

import com.example.mymarvel.api.exceptions.CharacterNotFoundException;
import com.example.mymarvel.api.exceptions.ObjectNotFoundException;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.LongUnaryOperator;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final ComicRepository comicRepository;

    public Character getCharacter(Long id)  {
        return characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException("Not found..."));
    }

    public List<Character> getAll() {
        List<Character> characters = characterRepository.findAll();
        if (characters.size() == 0) {
            throw new CharacterNotFoundException("Characters not found...");
        }
        return characterRepository.findAll();
    }

    public void save(Character character) {
        characterRepository.save(character);
    }

    public List<Comic> getComics(Long id) {
        List<Comic> comics = comicRepository.getComicsByCharacterId(id);
        if (comics.size() == 0) {
            throw new CharacterNotFoundException("List is empty.");
        }
        return comics;
    }
}
