package com.example.mymarvel.domain.comic;

import com.example.mymarvel.api.exceptions.CharacterNotFoundException;
import com.example.mymarvel.api.exceptions.ComicNotFoundException;
import com.example.mymarvel.api.exceptions.ObjectNotFoundException;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComicService {
    private final ComicRepository comicRepository;
    private final CharacterRepository characterRepository;

    public Comic getComic(Long id) {
        return comicRepository.findById(id).orElseThrow(() -> new ComicNotFoundException("Not found..."));
    }

    public void save(Comic comic) {
        comicRepository.save(comic);
    }

    public List<Comic> getAll() {
        List<Comic> comics = comicRepository.findAll();
        if (comics.size() == 0) {
            throw new ComicNotFoundException("Comics not found...");
        }
        return comicRepository.findAll();
    }

    public List<Character> getCharacters(Long id) {
        List<Character> characters = characterRepository.getCharactersByComicsId(id);
        if (characters.size() == 0) {
            throw new CharacterNotFoundException("List is empty.");
        }
        return characters;
    }

}
