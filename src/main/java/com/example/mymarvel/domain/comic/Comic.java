package com.example.mymarvel.domain.comic;

import com.example.mymarvel.domain.character.Character;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain=true)
@Entity
@Table(name = "Comic")
@ToString
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",unique = true, nullable = false,length = 100)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "comic_section"
            ,joinColumns = @JoinColumn(name = "comic_id")
            ,inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    List<Character> characters;

//    public void addComicToCharacter(Character character) {
//        if (characters == null) {
//            characters = new ArrayList<>();
//        }
//        characters.add(character);
//    }


}
