package com.example.mymarvel.domain.comic;

import com.example.mymarvel.domain.character.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComicRepository extends JpaRepository<Comic, Long> {
    @Query("select co from Comic co join Character ch where ch.id = :pId")
    List<Comic> getComicsByCharacterId(@Param("pId") Long id);

}
