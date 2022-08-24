package com.human.pet.domain;

//외장 라이브러리 (gradle로 다운로드한 롬북이 외장 라이브러리)
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

//원래는 setter, getter라는 메서드가 있어야 private 필드값에 데이터를 넣을 수 있지만,
//(gradle에서 설치)롬북 이라는 라이브러리로 자동 getter, setter 메서드 생성

//@Entity 이 class가 JPA통해 데이터베이스 테이블로 쓰겠다고 명시 해주는 속성
//롬북에 있는 Getter라는 메서드를 통해
//하단에 있는 클래스 board는
//자동으로 getter, setter 메서드가 생성됨을
//암시함(@어노테이션)
@Getter
@ToString
@Setter
@Entity
@Table
/*잠시 없앰
@Entity*/
public class Board {

    //@Id : PK (primary key) SQL문의 기본키
    //@GeneratedValue 자동생성 속성
    // 식별 필드

    @Id @GeneratedValue
    private Long seq;

    //@Column은 title 필드값을 컬럼화할 때 길이와 null 입력 가능여부 옵션
    @Column(length = 40, nullable = false)
    private String title;

//    @Column(updatable = false)
    @Column(nullable = false, updatable = false)
    private String writer;

    //@ColumnDefault 생성할 때 기본 데이터
    @Column(nullable = false)
    @ColumnDefault("'no content'")
    private String content;

    //타입이 날짜
    @Temporal(TemporalType.DATE)
//    @Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
    private Date createDate;

    @ColumnDefault("0")
    @Column(insertable = false, updatable = false)
//    @Column(insertable = false, updatable = false, columnDefinition = "number default 0")
    private long cnt;
}