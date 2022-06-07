package com.example.mymarvel.domain.comic;

import com.example.mymarvel.events.ComicSaveEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComicHandler {

    private final ComicService comicService;

    @EventListener(ComicSaveEvent.class)
    public void checkName(ComicSaveEvent event){
        Comic comic = (Comic) event.getSource();
        comicService.isNameUnique(comic);
    }

}
