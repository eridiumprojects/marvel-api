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


// добавить транзакции на методы (разобраться с propagation и isolation) и посмотреть как это работает
// на практике включив логи запросов через application.yaml - погуглить + включить отображение sql запросов в принципе

// попробовать транзакцию на приватном методе и выяснить почему так нельзя сделать

// сделать так, чтобы на старте приложения в базе уже были какие-то объекты
// через @PostConstruct на каком-то этапе в каком-то месте - погуглить
// сделать отдельный класс для этого и там прописать логику


// подключить spring-kafka к проекту, сделать высылку объектов как строк продюсер при сохраненнии объектов
// консьюмер должен просто печатать объекты в лог

// почитать про ассершены которые используются для тестов assertThat().что-нибудь() + assert... eq, throw, etc

// попробовать замокировать что-то в отдельном тестовом классе
/*
@Mock
Object object;

method() {
    Jopa jopa = new Jopa().setZalupa(4004);
    when(object).call(get()).thanReturn(jopa);
    assertEqauls(object.call(), jopa); // test passed
}

Controller controller; // it uses object internally

method() {
    Jopa jopa = new Jopa().setZalupa(4004).age(17);
    JopaDto jopa = new JopaDto().setZalupa(4004).setTeen(true);
    when(object).call(get(any())).thanReturn(jopa);
    assertEqauls(controller.get(1L), jopa); // test passed
}


@Spy - на изучение
 */

// создать на основе аннотаций (погуглить какие использовать) индексы для имен
// попробовать создать на живой бд тот же индекс через SQL и посмореть на этот индекс (научится как изучать эту хрень)

// просто изучить: markdown, git - изучить основные операции (fetch/pull/cherry-pick) и понятия (squash)
// maven lifecycle - 9 видов и как они связаны между собой (линейно)

// научиться дебажить на этом проекте что-нибудь и научится пользоваться эвалуатором

// swagger - подключить (springdoc зависимость)

//{id}/update