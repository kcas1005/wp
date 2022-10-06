package com.example.sample.model.entity;

import com.example.sample.model.response.CommentRest;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor //모든 필든를 매개변수로 가지는 생성자 생성
@NoArgsConstructor //디폴트 생성자 생성
@ToString
@Getter
@Setter
@SequenceGenerator(
        name="COMMENT_ID_SEQ_GEN",//시퀀스 제너레이터 이름
        sequenceName="COMMENT_ID_SEQ",//시퀀스 이름
        initialValue=1,//시작값
        allocationSize=1//메모리를 통해 할당할 범위 사이즈
)
public class CommentEntity {
    @Id
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,//사용할 전략을 시퀀스로  선택
            generator="COMMENT_ID_SEQ_GEN"//식별자 생성기를 설정해놓은  COMMENT_ID_SEQ_GEN으로 설정
    )
    private Integer id;
    @Column
    private Integer loginEntityId;
    @Column
    private String name;
    @Column
    public String contents;

    public CommentRest toRest(){
        return new CommentRest(loginEntityId,contents);
    }
}
