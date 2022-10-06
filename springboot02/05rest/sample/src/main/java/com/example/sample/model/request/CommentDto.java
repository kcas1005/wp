package com.example.sample.model.request;

import com.example.sample.model.entity.CommentEntity;
import lombok.*;

import javax.persistence.*;


@AllArgsConstructor //모든 필든를 매개변수로 가지는 생성자 생성
@NoArgsConstructor //디폴트 생성자 생성
@ToString
@Getter
@Setter
public class CommentDto {
    private Integer id;
    private Integer loginEntityId;
    private String name;
    private String contents;
    public CommentEntity toEntity(){
        return new CommentEntity(id,loginEntityId,name,contents);
    }
}
