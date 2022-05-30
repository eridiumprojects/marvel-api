package com.example.mymarvel.api.rests;

import com.example.mymarvel.domain.comic.Comic;
import com.example.mymarvel.domain.comic.ComicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/public/comic")
public class ComicRestController {
    private ComicService comicService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comic> getComic(@PathVariable("id") Long comicId) {
        if (comicId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Comic comic = this.comicService.getComic(comicId);

        if (comic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comic,HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comic> saveComic(@RequestBody Comic comic) {
        if (comic == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.comicService.save(comic);
        return new ResponseEntity<>(comic,HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comic> updateComic(@RequestBody Comic comic) {
        if (comic == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.comicService.save(comic);
        return new ResponseEntity<>(comic, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comic> deleteComic(@PathVariable("id") Long id) {
        Comic comic = this.comicService.getComic(id);

        if (comic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.comicService.delete(comic);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comic>> getAll() {
        List<Comic> comics = this.comicService.getAll();
        if (comics == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comics,HttpStatus.OK);

    }
}
