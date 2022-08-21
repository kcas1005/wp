package com.example.lecture_spring_2_crudproject.entity.board;


import com.example.lecture_spring_2_crudproject.entity.base.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long seq;

    @Setter
    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false, updatable = false)
    private String writer;

    @Setter
    @Column(nullable = false)
    @ColumnDefault("'no content'")
    private String content;

//    @Temporal(TemporalType.DATE)
//    private Date createDate;
//
//    @Temporal(TemporalType.DATE)
//    private Date updateDate;

    @Setter
    @ColumnDefault("0")
    @Column(insertable = false, updatable = false)
    private Long cnt;

    //deleteYn
}
