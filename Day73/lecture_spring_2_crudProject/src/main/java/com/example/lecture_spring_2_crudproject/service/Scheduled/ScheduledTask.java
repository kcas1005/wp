package com.example.lecture_spring_2_crudproject.service.Scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.lecture_spring_2_crudproject.service.textTransfer.SeleniumExample;

//@Component : container에 @Bean을 등록하는 어노테이션 -> @Component를 상속받은 대표적인 어노테이션이
//@Service, @Controller이 있다
@Component
public class ScheduledTask {
    //ScheduledTask 클래스와 SeleniumExample은 호출 당하고 호출하는 명확한 관계이므로 필드 주입 방식 선택
    @Autowired
    SeleniumExample seleniumExample;

    //@Scheduled 이 붙은 메서드는 주기적으로 실행
    //main메서드의 @SpringBootScheduling 어노테이션이 스택 명령이 들어가 있으면 Springboot은 런타임시점에서
    //@Scheduled 이 붙은 메서드를 찾고 설정된 값에 따라 주기적으로 반복 실행
    //fixedDelay : 메서드가 실행 완료 후 5초 뒤에 재실행
    //conn : 연도날짜시간 반복주기 등 옵션을 통해서 메서드가 반복 실행
    @Scheduled(fixedDelay = 5000)
    public void scheduledTest(){
        seleniumExample.scraping();
    }
}
