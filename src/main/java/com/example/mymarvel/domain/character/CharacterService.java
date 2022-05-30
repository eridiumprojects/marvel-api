package com.example.mymarvel.domain.character;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;

    public Character getCharacter(Long id) {
        return characterRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    public void save(Character character) {
        characterRepository.save(character);
    }

    public void delete(Character character) { characterRepository.delete(character);}
}
