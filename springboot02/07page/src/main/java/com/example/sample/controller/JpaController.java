package com.example.sample.controller;

import com.example.sample.dto.MemberDTO;
import com.example.sample.entity.Member;
import com.example.sample.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JpaController {

    @Autowired
    private MemberRepository memberRepository;

    //회원등록 회원수정
    @RequestMapping(value = "/jpa/memberWrite", method = RequestMethod.GET)
    public String memberWrite(
            @RequestParam(value = "num", required = false) Integer num,
            Model model
    ) {
        if (num != null) {
            //기존회원 수정
        } else {
            //신규
            System.out.println("신규 등록...");
            model.addAttribute("memberDTO", new MemberDTO());
            model.addAttribute("formTitle", "Registration");
        }

        return "jpa/memberWriteForm";
    }

    @RequestMapping(value = "/jpa/memberWrite", method = RequestMethod.POST)
    public String memberWrite(
            MemberDTO memberDto,
            Model model
    ) {
        try {
            System.out.println(memberDto);
            //memberDTO > entity
            Member save = memberDto.toEntity();
            //Repository를 이용한 DB작업
            Member saved = memberRepository.save(save);
            System.out.println(saved);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return "redirect:/";
    }

    //jpa/memberList
    @RequestMapping(value = "/jpa/memberList", method = RequestMethod.GET)
    public String memberList(
            Pageable pageable,
            @RequestParam(value="searchKeyword",required = false,defaultValue = "") String searchKeyword,
            Model model
    ) {

        //page기능 만들기
        //spring boot는 페이지 기능을 만들수 있는 클래스를 제공
        //Pageable      페이지 정보를 저장하는 객체
        //Page          검색된 데이터 저장
//        Page<Member> members = memberRepository.findAll(pageable);
        Page<Member> members = memberRepository.findByNameContaining(searchKeyword,pageable);

        System.out.println("전체 페이지 개수=" + members.getTotalPages());
        System.out.println("전체 레코드 개수=" + members.getTotalElements());
        System.out.println("현재 페이지 개수=" + members.getNumber());
        System.out.println("한 페이지에 보여주는 글 개수=" + members.getSize());
        System.out.println("정렬=" + members.getSort());
        System.out.println("number,size,sort=" + members.getPageable());

        model.addAttribute("members", members);
        return "jpa/memberList";
    }

    @RequestMapping(value = "/jpa/memberList1", method = RequestMethod.GET)
    public String memberList1(
            Model model
    ){
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "jpa/memberList1";
    }

    @RequestMapping(value = "/jpa/memberList2", method = RequestMethod.GET)
    public String memberList2(
            Model model
    ) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "jpa/memberList1";
    }

}
