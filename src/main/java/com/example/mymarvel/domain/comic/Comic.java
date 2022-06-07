package com.example.mymarvel.domain.comic;

import com.example.mymarvel.domain.character.Character;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.LazyCollection;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "comic")
@Table(name = "comics")
@ToString
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "comic_character__fk",
            joinColumns = @JoinColumn(name = "comic_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @Lazy
    List<Character> characters = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comic comic = (Comic) o;
        return name.equals(comic.name) && characters.equals(comic.characters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, characters);
    }
}