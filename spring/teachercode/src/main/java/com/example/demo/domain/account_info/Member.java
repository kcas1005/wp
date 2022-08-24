package com.example.demo.domain.account_info;

import com.example.demo.domain.base.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@ALLArgsConstructor : 모든 매개변수를 갖는 생성자
//@NoArgsConstructor : 매개변수 없는 생성자
//Builder

//@Entity JPA가 이 객체를 기준으로 table을 만들어야 한다고 선언
@ToString
@Entity
@Getter
@Setter
public class Member extends BaseTimeEntity {

    //SELECT [*컬럼명=객체의 필드] FROM TABLE_NAME*객체;
    //CREATE TABLE(
    //  seq Number primary key,
    //  id VARCHAR2(40) NOT NULL
    //    )
    //JPA : 객체에 맞춰서 SQL문으로 바꿔주는 것(번역)
    //@Id : table을 만들 때, 테이블의 튜플(row)를 식별할 수 있는 기본키

    //table을 만들때, 테이블의 튜플(row)를 식별할 수 있는 기본키
    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 40, nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

/*
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    private Date updateDate;
*/

}
