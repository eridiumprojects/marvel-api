package com.example.mymarvel.domain.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("select c from character c join c.comics co where co.id = :pId")
    List<Character> getCharactersByComicsId(@Param("pId") Long id);

    Optional<Character> findByName(String name);

}
