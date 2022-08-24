/*
package com.example.lecture_spring_2_crudproject.entity.board;


import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.entity.base.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long seq;

    @Setter
    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false, updatable = false)
    private String writer;

    @Setter
    @Column(nullable = false)
    @ColumnDefault("'no content'")
    private String content;

    //@ManyToOne 다양한 board는 1개의 member를 바라본다
    //member를 필드에 선언
    //참조키가 어디인지 선언 (member 기본키가 board의 참조키로 기본적으로 할당)
    //board의 writer는 member의 id와 연관되어 있고, 참조키로 id로 연결되어 있다
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Member member;

//    @Temporal(TemporalType.DATE)
//    private Date createDate;
//
//    @Temporal(TemporalType.DATE)
//    private Date updateDate;

    @Setter
    @ColumnDefault("0")
    @Column(insertable = false, updatable = false)
    private Long cnt;

    //deleteYn
}
*/
package com.example.lecture_spring_2_crudproject.entity.board;


import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.entity.base.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long seq;

    @Setter
    @Column(length = 40, nullable = false)
    private String title;

    @Column(nullable = false, updatable = false)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Member member;

    @Setter
    @Column(nullable = false)
    @ColumnDefault("'no content'")
    private String content;

    //@ManyToOne 다양한 board는 1개의 member를 바라본다
    //member를 필드에 선언
    //참조키가 어디인지 선언 (member 기본키가 board의 참조키로 기본적으로 할당)
    //board의 writer는 member의 id와 연관되어 있고, 참조키로 id로 연결되어 있다

//    @Temporal(TemporalType.DATE)
//    private Date createDate;
//
//    @Temporal(TemporalType.DATE)
//    private Date updateDate;

    @Setter
    @ColumnDefault("0")
    @Column(insertable = false, updatable = false)
    private Long cnt;

/*
    @OneToMany(mappedBy = "board")
    private List<Comments>
*/

    //deleteYn
}


