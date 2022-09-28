package com.example.demo.service.account_info;

import com.example.demo.domain.account_info.Member;

import java.util.List;

public interface MemberService {

    //Email 또는 ID를 조회하여 튜플을 찾기
    Member getMemberWhereIdOrEmail(String Email, String Id);

    Member getMemberWhereIdAndROWNUL1(String id);

    //    Member getM
    List<Member> getMemberList();

    void insertMember(Member member);

    Member getMember(Member member);

    void updateMember(Member member);

    void deleteMember(Member member);

}
