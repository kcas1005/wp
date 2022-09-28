/*
package com.example.lecture_spring_2_crudproject.entity.board;

import com.example.lecture_spring_2_crudproject.entity.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
public class Comments extends BaseTimeEntity {

    //builder
    //@Transient
    //@NoArgsConstructor(AccessLevel.PROTECTED)

    @Id
    private Long seq;

    private String Comments;

    @ManyToOne
    @JoinColumn(referencedColumnName = "title")
    private Board board;
}
*/
package com.example.lecture_spring_2_crudproject.entity.board;

import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.entity.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
public class Comments extends BaseTimeEntity {

    //builder
    //@Transient
    //@NoArgsConstructor(AccessLevel.PROTECTED)

    @Id
    private Long seq;

    private String comments;

    //게시글을 삭제하면 댓글도 삭제되야 됨
    @Transient
    private Long board_seq;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "board_seq", referencedColumnName = "seq")
    private Board board;

}
