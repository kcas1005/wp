package com.example.sample.model.request;

import com.example.sample.model.entity.LoginEntity;
import lombok.*;

@AllArgsConstructor //모든 필드를 매개변수로 가지는 생성자
@NoArgsConstructor  //기본생성자 생성
@Getter
@Setter
@ToString
public class LoginDto {
    private Integer id;
    private String name;
    private String password;

    public LoginEntity toEntity() {
        return new LoginEntity(id, name, password);
    }

}