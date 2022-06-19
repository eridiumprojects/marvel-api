package com.example.mymarvel.init;

import com.example.mymarvel.api.controllers.ComicController;
import com.example.mymarvel.api.dtos.ComicDto;
import com.example.mymarvel.api.dtos.ComicView;
import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicRepository;
import com.example.mymarvel.exceptions.ComicNotFoundException;
import com.sun.xml.bind.v2.schemagen.xmlschema.ContentModelContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbInit {
    @Autowired
    private ComicController comicController;
    @Autowired
    private ComicRepository comicRepository;

//    public Comic comic = new Comic();
    Long Id;

    @PostConstruct
    private void postConstruct() {
        ComicDto comicDto = new ComicDto();
        comicDto.setName("Dddd");
        comicDto.setCharacterNames(List.of("Jaga-jaga", "Surguz"));
        comicController.saveComic(comicDto);
        Comic comic = comicRepository.findByName("Dddd")
                .orElseThrow(() -> new ComicNotFoundException("Not found..."));
        Id = comic.getId();
    }

    @PreDestroy
    private void preDestroy() {
        comicController.deleteComic(Id);
    }
}
