package com.example.mymarvel.domain.character;

import com.example.mymarvel.domain.comic.Comic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = " Characters")
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
    @Lazy

    List<Comic> comics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return name.equals(character.name) && comics.equals(character.comics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, comics);
    }
}
