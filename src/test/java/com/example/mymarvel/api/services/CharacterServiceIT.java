package com.example.mymarvel.api.services;

import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.character.CharacterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class CharacterServiceIT {
    @Mock
    CharacterService characterService;

    Character character = new Character().
            setName("Aboba").
            setId(1L);

    @Test
    void saveMockCharacter() {
        when(characterService.save(character)).thenReturn(character);
        assertEquals(character, characterService.save(character));
    }


}
