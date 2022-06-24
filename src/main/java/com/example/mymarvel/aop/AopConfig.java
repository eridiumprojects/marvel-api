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
