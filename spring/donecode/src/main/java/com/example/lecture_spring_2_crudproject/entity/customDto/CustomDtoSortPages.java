package com.example.lecture_spring_2_crudproject.entity.customDto;

import javax.persistence.Id;

public class CustomDtoSortPages {
    @Id
    private Long Seq;
    private String PREVID;
    private String PREV_SUBJECT;
    private String NEXTID;
    private String NEXT_SUBJECT;
}
