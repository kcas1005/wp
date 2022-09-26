package com.example.sample.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor //모든 필드를 매개변수로 가지는 생성자
@NoArgsConstructor  //기본생성자 생성
@Getter
@Setter
@ToString
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



    /*
    @NoArgsConstructor를 사용하므로써 밑에 주석 처리 된 것을 다 처리해 줌
    public LoginEntity(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }*/
}