package com.example.sample.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor //모든 필드를 매개변수로 가지는 생성자
@NoArgsConstructor  //기본생성자 생성
@Getter
@Setter
@ToString
public class LoginEntity {
    @Id
    @GeneratedValue
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