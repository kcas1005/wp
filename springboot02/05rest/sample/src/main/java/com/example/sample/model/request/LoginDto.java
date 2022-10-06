package com.example.sample.model.request;

import com.example.sample.model.entity.LoginEntity;

public class LoginDto {

    private Integer id;
    private String name;
    private String password;

    public LoginEntity toEntity(){
        return new LoginEntity(id,name,password);
    }

    public LoginDto(){}
    public LoginDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public LoginDto(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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
}
