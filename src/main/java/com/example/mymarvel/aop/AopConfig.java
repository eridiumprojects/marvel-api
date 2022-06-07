//package com.example.mymarvel.aop;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//@Slf4j
//public class AopConfig {
//
//
//    @Around("execution(* com.example.mymarvel.domain.comic.ComicService.getCharacters(..))")
//    public void setLog(){
//        log.info("Comic entity has been saved");
//    }
//}
//
///*
//1. логика проверки уникальности при сохранении
//2 логирование сохранения новых объектов через аоп
//3 метод редактирования имени объекта по айди (для обеих сущностей) - не забыть проверку на уникальность имени
//4 интеграционные тесты (embed postgres, sql before)
// */