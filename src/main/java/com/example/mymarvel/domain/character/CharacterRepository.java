package com.example.mymarvel.domain.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character,Long> {
    @Query("select ch from Character ch join Comic co where co.id = :pId")
    List<Character> getCharactersByComicsId(@Param("pId") Long id);

}
