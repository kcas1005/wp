package com.example.sample.model.response;


import lombok.*;

@AllArgsConstructor //모든 필든를 매개변수로 가지는 생성자 생성
@NoArgsConstructor //디폴트 생성자 생성
@ToString
@Getter
@Setter
public class CommentRest{
       private Integer loginEntityId ;
       private String contents;
}
