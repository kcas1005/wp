package com.example.demo.domain;

/**
* @package : com.example.demo.domain
* @name : Board.java
* @date : 2022-08-08 오후 6:18
* @author : Rubisco
* @version : 1.0.0
* @modifyed :
* @description : 게시판 도메인
**/

import com.example.demo.domain.base.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

//@Entity 이 class가 JPA통해 데이터베이스 테이블로 쓰겠다고 명시 해주는 속성
@Getter
@ToString
@Entity
@Setter
public class Board extends BaseTimeEntity{

    //@Id : PK (primary key) SQL문의 기본키
    //@GeneratedValue 자동생성 속성
    @Id @GeneratedValue
    private Long seq;

    //@Column은 title 필드값을 컬럼화할 때 길이와 null 입력 가능여부 옵션
    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false, updatable = false)
    private String writer;

    //@ColumnDefault 생성할 때 기본 데이터
    @Column(nullable = false)
    @ColumnDefault("'no content'")
    private String content;

/*
    //타입이 날짜
    @Temporal(TemporalType.DATE)
    private Date createDate;
*/

    @ColumnDefault("0")
    @Column(insertable = false, updatable = false)
    private Long cnt;

}
