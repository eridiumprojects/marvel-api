package com.example.mymarvel.domain.comic;

import com.example.mymarvel.api.dtos.UpdatedComic;
import com.example.mymarvel.exceptions.ComicAlreadyExistException;
import com.example.mymarvel.exceptions.ComicNotFoundException;
import com.example.mymarvel.domain.character.CharacterRepository;
import com.example.mymarvel.events.ComicSaveEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComicService {
    private final ComicRepository comicRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Comic getComic(Long id) {
        return comicRepository.findById(id).orElseThrow(() -> new ComicNotFoundException("Not found..."));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void save(Comic comic) {
        publisher.publishEvent(new ComicSaveEvent(comic));
        isNameUnique(comic.getName());
        comicRepository.save(comic);
    }

    @Transactional
    public List<Comic> getAll() {
        return comicRepository.findAll();
    }

    public void isNameUnique(String name) {
        Optional<Comic> comicOptional = comicRepository.findByName(name);
        if (comicOptional.isPresent()) {
            throw new ComicAlreadyExistException("Unique failed error.");
        }
    }

    @Transactional
    public void delete(Comic comic) {
        comicRepository.delete(comic);
    }

    @Transactional
    public void update(UpdatedComic updatedComic) {
        Comic comic = comicRepository.
                findById(updatedComic.getId()).
                orElseThrow(() -> new ComicNotFoundException("Not found..."));
        isNameUnique(updatedComic.getNewName());
        comic.setName(updatedComic.getNewName());
        comicRepository.save(comic);
    }
}
