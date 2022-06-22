package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.CharacterDto;
import com.example.mymarvel.api.dtos.CharacterView;
import com.example.mymarvel.domain.character.Character;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class CharacterMapperIT {

    private final CharacterMapper characterMapper =  new CharacterMapper();

    @Mock
    private ComicRepository comicRepository;

    @InjectMocks
    Character character = new Character();
    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void toView() {
        Character character = new Character();
        character.setId(1L);
        character.setName("Aboba");
        CharacterView characterView = characterMapper.toView(character);
        assertEquals(characterView.getName(),character.getName());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void toViews() {
        List<Character> characters = new ArrayList<>();
        Character character1 = new Character();
        character1.setName("Aboba");
        character1.setId(1L);
        characters.add(character1);
        List<CharacterView> characterViews = characterMapper.toViews(characters);
        assertEquals(characterViews.get(0).getName(),(characters.get(0).getName()));

    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void toCharacter() {
        CharacterDto characterDto = new CharacterDto();
        characterDto.setName("Spider-man");
        characterDto.setComicId(List.of(1L));
        Comic optionalComic = new Comic().setId(1L);

        when(comicRepository.findById(any())).thenReturn(Optional.of(optionalComic));
        Character character = characterMapper.toCharacter(characterDto);
        assertEquals(character.getComics().get(0).getId(),optionalComic.getId());
    }
}