package com.example.lecture_spring_2_crudproject.repository.account;


import com.example.lecture_spring_2_crudproject.entity.account.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//MemberRepository는 CrudRepository를 상속받아 기능을 온전히 씀
//CrudRepository : JPA를 통해 DB에 기본적인 SQL문을 통해 소통 (INSERT INTO, SELECT, UPDATE, DELETE)
public interface MemberRepository extends JpaRepository<Member, Long> {

//    List<Member> findByIdOrEmail(String email);

    //Return 내용선언, Find~변수명에 맞춰서 메서드 생성, 필요한 매개변수
/*
    @Query(value = "select M from Member M where M.email = :email_1 or M.id = :id_1")
    Member findMemberByEmailOrId(String email_1, String id_1);
*/
    @Query(value = "SELECT * FROM Member M WHERE M.email = :email_1 or M.id = :id_1", nativeQuery = true)
    Member findMemberByEmailOrId(String email_1, String id_1);

    //(ID는 중복가능한 구조에서)Id값을 매개변수로 넣고, 아이디 생성날짜가 가장 최신인 것
/*
    @Query(value = "select M from Member M where M.id = :id_1 order by M.createDate DESC")
    Member findFirstById(String id_1);
*/

    @Query(value = "select * from Member M where  M.id = :id_1 ", nativeQuery = true)
    Member findFirstById(String id_1);

//    List<Member> findAllByEmailContainsOrderByCreateDateDesc (String a);
    /*
    email 주소 일부분만 입력
    if 비슷한 주소가 있으면 정확한 email 주소를 찾는 로직 구현
    if 비슷한 이메일이 있으면 id와 password를 입력받는다
    이상이 있다면 비밀번호 변경 로직 진행
    */
}
