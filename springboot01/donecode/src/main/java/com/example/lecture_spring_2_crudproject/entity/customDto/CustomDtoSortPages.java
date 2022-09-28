package com.example.lecture_spring_2_crudproject.entity.customDto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CustomDtoSortPages {
    @Id
    private Long Seq;
    private String PREVID;
    private String PREV_SUBJECT;
    private String NEXTID;
    private String NEXT_SUBJECT;
}
