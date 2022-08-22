package com.example.lecture_spring_2_crudproject.entity.account;

import com.example.lecture_spring_2_crudproject.entity.base.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

//@AllArgsConstructor : 모든 매개변수를 갖는 생성자
//@NoArgsConstructor(access = AccessLevel.PROTECTED) : 매개변수 없는 생성자
//@Builder

//@Entity JPA가 이 객체를 기준으로 table을 만들어야 한다고 선언
@ToString
@Entity
@Getter
@Setter
public class Member extends BaseTimeEntity {

    //SELCT [*컬럼명=객체의 필드] FROM TABLE_NAME*객체;
    //CREATE TABLE (
//        seq NUMBER primary key,
//        id  VARCHAR2(40) NOT NULL
//    )
    //JPA : 객체에 맞춰서 SQL문으로 바꿔주는 것 (번역)
    //@Id : table을 만들 때, 테이블의 튜플(row)를 식별할 수 있는 기본키
    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 40)
    private String id;

    private String password;

    private String email;

    //deleteYn

}

//
//@ToString
//@Entity
//public class Member {
//
//    @Id
//    @GeneratedValue
//    private Long seq;
//
//    @Column(length = 40, nullable = false)
//    private String id;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Temporal(TemporalType.DATE)
//    private Date createDate;
//
//    @Temporal(TemporalType.DATE)
//    private Date updateDate;
//
////    @ColumnDefault("false")
////    @Column(insertable = false, updatable = false)
////    private boolean deleteYN;
//}
