package com.example.sample.dto;

import com.example.sample.Entity.Member;

public class MemberDto {

    // Fields
    //DTO DB와 관련없는 데이터를 처리할때 사용
    private int num;
    private String name;
    private String id;
    private String phone;
    private int age;

    // Getters and Setters
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // toString()
    @Override
    public String toString() {

        return "MemberDTO [num=" + num + ", name=" + name + ", id=" + id + ", phone=" + phone + "]";
    }

    // toEntity() Dto가 Entity클래스로 값을 넣어준다.
    public Member toEntity() {

        return new Member( num, name, id, phone, age );
    }
}

