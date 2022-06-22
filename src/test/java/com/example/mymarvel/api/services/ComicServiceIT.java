package com.example.mymarvel.api.services;

import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicService;
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
class ComicServiceIT {

    @Mock
    ComicService comicService;

    Comic comic = new Comic().
            setName("DC").
            setId(1L);

    @Test
    void saveMockComic() {
        when(comicService.save(comic)).thenReturn(comic);
        assertEquals(comic, comicService.save(comic));
    }
}
