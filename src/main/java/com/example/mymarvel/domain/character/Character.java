package com.example.mymarvel.domain.character;

import com.example.mymarvel.domain.comic.Comic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "Characters")
@ToString
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "comic_section"
            ,joinColumns = @JoinColumn(name = "character_id")
            ,inverseJoinColumns = @JoinColumn(name = "comic_id")
    )
    List<Comic> comics;

//    public void addCharacterToComic(Comic comic) {
//        if (comics == null) {
//            comics = new ArrayList<>();
//        }
//        comics.add(comic);
//    }




}
