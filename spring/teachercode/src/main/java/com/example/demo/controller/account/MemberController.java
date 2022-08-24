package com.example.demo.controller.account;

import com.example.demo.domain.Board;
import com.example.demo.domain.account_info.Member;
import com.example.demo.service.account_info.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

//[디스페처 서블릿]이 컨트롤러를 찾기 위해서 @Controller라고 선언
@Controller
@RequestMapping(path = "/account")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;

    //(클라이언트가 두 분류)게시판 : 사용자관점,
    //시스템관리관점(회원관리, 게시판관리, 컨텐츠관리) [웹솔루션을 관리하는 오너]
    //getAccountList : 전체 회원 목록 보기 : 웹솔루션에서 웹시스템을 관리하는 관리자를 기능
    //public : 전부공개
    //String: 이 메서드가 실행 완료되면 최정적으로 리턴하는 타입(HTML 파일명을 찾기 위해)

    //member : 클라이언트에서 서버로 데이터를 받는 Entity
    //model : 서버에서 클라이언트로 데이터를 전송하는 매개체
    @GetMapping("/getAccountList")
    public String getAccountList(Model model){
        System.out.println("-----------Get Accountlist----------");
        //model : 컨트롤러에 작업한 결과물을 HTML에 전달하기 위한 매개체
        //addAttribute : key/value으로 데이터를 저장하는 메서드
        //attributeName(key) : 뒤에 있는 value를 호출하기 위한 문자열(key)
        //memberService.getMemberList() : @Autowired로 선언된 MemberService 클래스를 호출하여
        //getMemberList()메서드 실행
        model.addAttribute("memberList", memberService.getMemberList());
        System.out.println("memberService.getMemberList() : " + memberService.getMemberList());
        return "/account/getAccountList";
    }
    @GetMapping("/getAccount")
    public String getAccount(Member member, Model model) {
        System.out.println("-----------Get Account----------");
        model.addAttribute("member", memberService.getMember(member));
        System.out.println("memberService.getMember(member) : " + memberService.getMember(member));
        return "/account/getAccount";
    }

    //updateAccount : 회원정보 수정
    @PostMapping("/updateAccount")
    public String updateAccount(Member member){
        System.out.println("-------Post update-------");
        memberService.updateMember(member);
        System.out.println("member : " + member);
        return "redirect:/account/getAccountList?seq="+member.getSeq();
    }
    @GetMapping("/updateAccount")
    public String updateAccountView(Member member, Model model) {
        System.out.println("-------Get update-------");
        model.addAttribute("member", memberService.getMember(member));
        System.out.println("memberService.getMember(member) : " + memberService.getMember(member));
        return "/account/insertAccount";
    }

    //deleteAccount : 회원정보 삭제
    @PostMapping("/deleteAccount")
    public String deleteAccount(Member member){
        System.out.println("-------Post delete-------");
        memberService.deleteMember(member);
        System.out.println("member : " + member);
        return "redirect:/account/getAccountList";
    }

    //기존데이터의 무결성 체크를 위한 데이터전체 조회과 일부 수정작업 (sql 특정 컬럼의 값을 모두 gmail.com > naver.com)
    //+백업 entity
    //회원정보가 일정 수치까지 다다르면(혹은 이벤트가 발생) updateAccountALL이라는 메서드를 통해
    //기존 entity의 테이블의 정보를 모두 백업 entity에 저장
    //crudReposity를 보면 인터페이스 메서드 findAll 회원정보 모두 불러온 뒤에 SaveAll메서드로 저장

    //+회원정보 1개의 테이블에서 관리하지 않아요 > 1년 지난 회원은 로그인을 안한 장기 휴식회원
    //+1년 미접속 계정은 따로 테이블에 옮겨서 저장 (예전 스타일)
    //날짜별로 1년이 지나면 새로 테이블을 생성해서 회원을 관리합니다.(회원가입한 날짜) >
    // > 장점 : 최신회원들을 관리하는 마케팅부서에서 도움
    // > 장점 : DB백업할 때도 테이블 파편화로 트랜잭션 위험 또는 시간 절약
    // > 단점 : Admin(관리자)는 모든 회원정보를 볼 때 다수의 테이블을 동시에 봐야 하기 때문에 JOIN을 써서
    //속도가 느림

    //return 타입이 String이유 : HTML 파일명을 찾기 위해
    @GetMapping("/insertAccount")
    public String insertAccountView(){
        System.out.println("-------Get insert-------");
        return "/account/insertAccount";
    }
    //Member 라는 매개변수로 Controller에 전달
    //Member(Entity)이고 DTO(Data Transfer Object)
    //어디선가 받거나 만든 데이터를 객체로 만드는 것 : DTO
    @PostMapping("/insertAccount")
    public String insertAccount(Member member){
        System.out.println("-------Post insert-------");
        //클라이언트에서 ID/PW
        //createDate
        //updateDate
        member.setCreateDate(new Date());
        member.setUpdateDate(new Date());
        memberService.insertMember(member);
        System.out.println("member : " + member);
        return "redirect:/account/getAccountList";
    }
    @GetMapping("/selectAccount")
    public String selectAccount() {
        System.out.println("----------Get selectAccount----------");
        return "account/selectAccount";
    }

    @PostMapping("/selectAccount")
    public String resultAccount(Member member, Model model) {
        System.out.println("----------Post selectAccount----------");
        model.addAttribute("memberList",
                memberService.getMemberWhereIdOrEmail(member.getEmail(), member.getId()));
        System.out.println(memberService.getMemberWhereIdOrEmail(member.getEmail(), member.getId()));
        return "account/resultAccount";
    }

}
