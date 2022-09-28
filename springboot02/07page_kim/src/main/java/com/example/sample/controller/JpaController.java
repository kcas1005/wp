package com.example.sample.controller;

import com.example.sample.Entity.Member;
import com.example.sample.dto.MemberDto;
import com.example.sample.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JpaController {

    private final MemberRepository memberRepo;

    @Autowired
    protected JpaController(MemberRepository memberRepo){
        this.memberRepo = memberRepo;
    }

    //회원등록, 수정 DB에서 null 처리하려면 Int대신 Integer를 사용해야 한다.
    @GetMapping("/jpa/memberWrite")
    public String memberWrite(
            @RequestParam(value = "num", required = false) Integer num, Model model){
        if(num != null){
            //기존회원 수정
            System.out.println("기존회원...");
        }
        else{
            //신규등록
            System.out.println("신규등록...");
            model.addAttribute("memberDto", new MemberDto());
            model.addAttribute("formTitle", "Registration");
        }
        return "jpa/memberWriteForm";
    }
    @PostMapping("/jpa/memberWrite")
    public String memberWrite(MemberDto memberDto) {
        try{
            System.out.println("memberDto= " + memberDto);
            //memberDto > entity
            Member save =  memberDto.toEntity();
            //Repository 를 이용한 DB작업
            Member saved = memberRepo.save(save);
            System.out.println("saved= " + saved);
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/jpa/memberList")
    public String memberList(Pageable pageable, Model model){
        Page<Member> members = memberRepo.findAll(pageable);
        System.out.println("-----GET memberList-----");
        System.out.println("전체 page 수= " + members.getTotalPages());
        System.out.println("전체 글 수= " + members.getTotalElements());
        System.out.println("현재 page= " + members.getNumber());
        System.out.println("한페이지에 보여지는 글 수= " + members.getSize());
        System.out.println("정렬= " + members.getSort());
        System.out.println("현재page, 전체page, sort= " + members.getPageable());
        model.addAttribute("members", members);
        return "jpa/memberList";
    }

    @GetMapping("/jpa/memberList1")
    public String memberList1(Model model){
        List<Member> members = memberRepo.findAll();
        model.addAttribute("members", members);
        return "jpa/memberList1";
    }

    @GetMapping("/jpa/memberList2")
    public String memberList2(@RequestParam(value = "searchKeyword", required = false, defaultValue = "")
                                  String searchKeyword, Pageable pageable, Model model){
        Page<Member> members = memberRepo.findByNameContaining(searchKeyword, pageable);
        System.out.println("-----GET memberList2-----");
        System.out.println("members= " + members);
        model.addAttribute("members", members);
        return "jpa/memberList2";
    }

}
