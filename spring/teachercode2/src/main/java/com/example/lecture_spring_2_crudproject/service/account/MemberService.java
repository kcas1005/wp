package com.example.lecture_spring_2_crudproject.service.account;


import com.example.lecture_spring_2_crudproject.entity.account.Member;

import java.util.List;

public interface MemberService {

    //Email또는ID를 조회하여 튜플을 찾기
    Member getMemberWhereIdOrEmail(String Email, String Id);

    Member getMemberWhereIdAndROWNUL1(String id);

    List<Member> getMemberList();

    void insertMember(Member member);

    Member getMember(Member member);

    void updateMember(Member member);

    void deleteMember(Member Member);

    boolean searchUserYnUseEmail(Member member);
    boolean searchUserYnUseId(Member member);
    boolean searchUserYnUsePassword(Member member);
}
