package com.example.mymarvel.api.mappers;

import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.dtos.ComicView;
import com.example.mymarvel.domain.comic.Comic;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComicMapperIT {
    private ComicMapper comicMapper = new ComicMapper();

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void toView() {
        Comic comic = new Comic();
        comic.setName("Marvel");
        comic.setId(1L);
        ComicView comicView = comicMapper.toView(comic);
        assertEquals(comicView.getName(),comic.getName());

    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void toViews() {
        List<Comic> comics = new ArrayList<>();
        Comic comic1 = new Comic();
        comic1.setName("Ababa");
        comic1.setId(1L);
        comics.add(comic1);
        List<ComicView> comicsViews = comicMapper.toViews(comics);
        assertEquals(comics.get(0).getName(),comicsViews.get(0).getName());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "./init/scripts/embed.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "./init/scripts/destroy.sql")
    void toComic() {
        ComicDto comicDto = new ComicDto();
        comicDto.setName("DC");
        comicDto.setCharacterNames(List.of("Boba"));
        Comic comic = comicMapper.toComic(comicDto);
        assertEquals(comic.getName(),comicDto.getName());
    }
}