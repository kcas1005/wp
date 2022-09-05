package com.example.lecture_spring_2_crudproject;

import com.example.lecture_spring_2_crudproject.entity.account.Member;
import com.example.lecture_spring_2_crudproject.entity.board.Board;
import com.example.lecture_spring_2_crudproject.repository.account.MemberRepository;
import com.example.lecture_spring_2_crudproject.repository.board.BoardRepository;
import com.example.lecture_spring_2_crudproject.service.Test_1.Test_1;
import com.example.lecture_spring_2_crudproject.service.openAPI.PublicAPI;
import com.example.lecture_spring_2_crudproject.service.textTransfer.SeleniumExample;
import com.example.lecture_spring_2_crudproject.service.textTransfer.TextTransfer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LectureSpring2CrudProjectApplicationTests {


    @Autowired
    TextTransfer textTransfer;

    @Autowired
    PublicAPI publicAPI;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    SeleniumExample selenium;

    @Autowired
    Test_1 textTest;

    @Test
    void apiTest() {
        publicAPI.testAPI();
    }

    @Test
    @DisplayName("저장, 데이터가 잘 들어갔는지 확인")
    void contextSave() {
        //Setter로 엔티티를 생성하고 repositoy가 정상 작동하는지 확인
        Member member = new Member();
        //클라이언트에서 controller에 데이터를 전달하는 내용을 setter를 통해 대체
        member.setId("humanClass4");
        member.setPassword("12341234@");
        member.setEmail("class4@123.com");
        //memberRepository의 save메서드가 정상 동작하는지 확인
        memberRepository.save(member);

        Board board = new Board();
                board.setContent("1234");
                board.setTitle("123");
                board.setWriter("123");
        boardRepository.save(board);

    }

    @Test
    void textTest() throws Exception {
        textTransfer.transferText3Word("abcdefg@gmil.com");
    }

    @Test
    void Scraping() {
        selenium.scraping();}

    @Test
    void Test_1_print() {
        textTest.Test_print();
    }

    @Test
    void Test_findMemberByEmailOrId(){
        memberRepository.findMemberByEmailOrId(null,"지성진");
    }
    /*@Test
    void Test_findMember_native(){
        memberRepository.findMemberById_native("지성진");
    }*/

}
