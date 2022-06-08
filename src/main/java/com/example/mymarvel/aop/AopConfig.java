package com.example.mymarvel.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect
@Component
@Slf4j
public class AopConfig {

    @After("execution(* com.example.mymarvel.domain.comic.ComicRepository.save(..))")
    public void saveComicSetLog() {
        log.info("Comic entity has been saved");
    }

    @After("execution(* com.example.mymarvel.domain.character.CharacterRepository.save(..))")
    public void saveCharacterSetLog() {
        log.info("Character entity has been saved");
    }

}

//*
//1 метод редактирования имени объекта по айди (для обеих сущностей) - не забыть проверку на уникальность имени
//2 логирование сохранения новых объектов через аоп
//3 логика проверки уникальности при сохранении
//4 интеграционные тесты (embed postgres, sql before)
// */