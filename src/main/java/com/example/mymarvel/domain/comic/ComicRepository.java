package com.example.mymarvel.domain.comic;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ComicRepository extends JpaRepository<Comic, Long> {
}
