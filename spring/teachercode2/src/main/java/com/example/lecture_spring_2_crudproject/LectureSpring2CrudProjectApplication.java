package com.example.lecture_spring_2_crudproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LectureSpring2CrudProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LectureSpring2CrudProjectApplication.class, args);
    }

}
