package com.example.mymarvel.events;

import com.example.mymarvel.domain.comic.Comic;
import org.springframework.context.ApplicationEvent;

public class ComicSaveEvent extends ApplicationEvent {
    public ComicSaveEvent(Comic comic) {
        super(comic);
    }
}
