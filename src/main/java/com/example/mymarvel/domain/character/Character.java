package com.example.mymarvel.domain.character;

import com.example.mymarvel.domain.comic.Comic;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "character")
@Tag(name = "character", description = "The character API")
@Table(name = "characters", indexes = {
        @Index(columnList = "name ASC", name = "character_name"),
})
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false, length = 100)
//    @ApiModelProperty(value = "name of character", example = "Harley-Queen")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "comic_character__fk",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id")
    )
    @Lazy
    private List<Comic> comics = new ArrayList<>();

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

    public String toString() {
        return "Character(id=" + this.getId() + ", name=" + this.getName() + ", comics=" + this.getComicList() + ")";
    }

    private String getComicList() {
        return getComics().stream().map(Comic::getName).toList().toString();
    }
}