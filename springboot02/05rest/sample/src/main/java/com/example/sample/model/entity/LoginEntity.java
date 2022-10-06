package com.example.sample.model.entity;

import lombok.*;

import javax.persistence.*;

//룸복예제..
//@Setter   getter setter 자동 추가
//@Getter
@Entity
@AllArgsConstructor //모든 필든를 매개변수로 가지는 생성자 생성
@NoArgsConstructor //디폴트 생성자 생성
@ToString
@Getter
@Setter
@SequenceGenerator(
        name="LOGIN_ID_SEQ_GEN",//시퀀스 제너레이터 이름
        sequenceName="LOGIN_ID_SEQ",//시퀀스 이름
        initialValue=1,//시작값
        allocationSize=1//메모리를 통해 할당할 범위 사이즈
)
public class LoginEntity {
    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,//사용할 전략을 시퀀스로  선택
            generator="LOGIN_ID_SEQ_GEN"//식별자 생성기를 설정해놓은  LOGIN_ID_SEQ_GEN으로 설정
    )
    private Integer id;
    @Column
    private String name;
    @Column
    private String password;
}
