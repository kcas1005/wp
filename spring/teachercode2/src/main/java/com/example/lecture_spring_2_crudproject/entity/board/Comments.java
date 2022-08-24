package com.example.lecture_spring_2_crudproject.entity.board;

import com.example.lecture_spring_2_crudproject.entity.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
public class Comments extends BaseTimeEntity {

    //builder
    //@Transient
    //@NoArgsConstructor(AccessLevel.PROTECTED)

    @Id
    private Long seq;

    private String Comments;

    @ManyToOne
    @JoinColumn(referencedColumnName = "title")
    private Board board;
}
