package com.example.mymarvel.domain.character;

import com.example.mymarvel.domain.comic.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Long> {

}
