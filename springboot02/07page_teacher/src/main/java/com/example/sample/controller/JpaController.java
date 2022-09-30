package com.example.sample.controller;

import com.example.sample.dto.MemberDTO;
import com.example.sample.entity.Member;
import com.example.sample.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JpaController {

    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping(value = "/jpa/memberDetail", method = RequestMethod.GET)
    public String memberDetali(@RequestParam(value = "num", required = false) Integer num, Model model) {
        System.out.println(num);
        Member member = memberRepository.findById(num).orElse(null);
        model.addAttribute("member", member);
        return "jpa/memberDetail";
    }

    @RequestMapping(value = "/jpa/memberDelete", method = RequestMethod.GET)
    public String memberDelete(@RequestParam(value = "num", required = false) Integer num) {
        System.out.println(num);
        memberRepository.deleteById(num);
        return "redirect:/";
    }

    //회원등록 회원수정
    @RequestMapping(value = "/jpa/memberWrite", method = RequestMethod.GET)
    public String memberWrite(@RequestParam(value = "num", required = false) Integer num, Model model) {
        if (num != null) {
            //기존회원 수정
            Member member = memberRepository.findById(num).orElse(null);
            model.addAttribute("memberDTO", member);
            model.addAttribute("formTitle", "Modification");
        } else {
            //신규
            System.out.println("신규 등록...");
            model.addAttribute("memberDTO", new MemberDTO());
            model.addAttribute("formTitle", "Registration");
        }

        return "jpa/memberWriteForm";
    }

    @RequestMapping(value = "/jpa/memberWrite", method = RequestMethod.POST)
    public String memberWrite(MemberDTO memberDTO, Model model) {
        try {
            if (memberDTO.getNum() == 0) {
                System.out.println(memberDTO);
                //memberDTO > entity
                Member save = memberDTO.toEntity();
                //Repository 를 이용한 DB작업
                Member saved = memberRepository.save(save);
            } else {
                System.out.println("@Query 수정...");
                //수정
                int saved = memberRepository.updateMemberQuery(memberDTO.getNum(), memberDTO.getName(), memberDTO.getAge());
                System.out.println(saved);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/jpa/memberWrite2", method = RequestMethod.POST)
    public String memberWrite2(MemberDTO memberDTO, Model model) {
        try {
            System.out.println(memberDTO);
            //memberDTO > entity
            Member save = memberDTO.toEntity();
            //Repository 를 이용한 DB작업
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
            @PageableDefault(size=5,sort="name",direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "searchCate", required = false, defaultValue = "") String searchCate, @RequestParam(value = "searchKeyword", required = false, defaultValue = "") String searchKeyword, Model model) {
        System.out.println(pageable);
        System.out.println(searchCate);
        System.out.println(searchKeyword);
        Page<Member> members = null;
        if (searchCate.equals("name")) {
            members = memberRepository.findByNameContaining(searchKeyword, pageable);
        } else if (searchCate.equals("id")) {
            members = memberRepository.findByIdContains(searchKeyword, pageable);
        } else if (searchCate.equals("phone")) {
            members = memberRepository.findByPhoneContaining(searchKeyword, pageable);
            // members = memberRepository.findByPhone(searchKeyword, pageable);

            // findByPhoneLike
            // members = memberRepository.findByPhoneLike(searchKeyword +"%", pageable);

            // findByPhoneStartsWith
            // members = memberRepository.findByPhoneStartsWith(searchKeyword, pageable);

            // findByPhoneEndsWith
            // members = memberRepository.findByPhoneEndsWith(searchKeyword, pageable);

            // 대소문자 구분
            //members = memberRepository.findByIdContains(searchKeyword, pageable);


        } else if (searchCate.equals("nameid")) {
            String[] result = searchKeyword.split(",");
            members = memberRepository.findByNameAndId(result[0], result[1], pageable);
        } else {
//            pageable = PageRequest.of(page,size);
//            pageable = PageRequest.of(page,size,sort);
//            pageable = PageRequest.of(1,3,Sort.by("name").ascending());
//            pageable = PageRequest.of(page,size,sort,direction);
            pageable = PageRequest.of(pageable.getPageNumber(),3,
                    Sort.by("age").descending().and(Sort.by("name").ascending()));

            members = memberRepository.findAll(pageable);
        }


        //page기능 만들기
        //spring boot는 페이지 기능을 만들수 있는 클래스를 제공
        //Pageable   페이지 정보를 저장하는 객채
        //Page       검색된 제이터 저장
        //Page<Member> members=memberRepository.findAll(pageable);
//        Page<Member> members = memberRepository.findByNameContaining(searchKeyword, pageable);


        model.addAttribute("searchCate", searchCate);
        model.addAttribute("members", members);
        model.addAttribute("searchKeyword", searchKeyword);
        return "jpa/memberList";
    }

    @RequestMapping(value = "/jpa/memberList3", method = RequestMethod.GET)
    public String memberList3(Pageable pageable, @RequestParam(value = "searchKeyword", required = false, defaultValue = "") String searchKeyword, Model model) {
        //page기능 만들기
        //spring boot는 페이지 기능을 만들수 있는 클래스를 제공
        //Pageable   페이지 정보를 저장하는 객채
        //Page       검색된 제이터 저장
        //Page<Member> members=memberRepository.findAll(pageable);
        Page<Member> members = memberRepository.findByNameContaining(searchKeyword, pageable);

        model.addAttribute("members", members);
        model.addAttribute("searchKeyword", searchKeyword);
        return "jpa/memberList3";
    }


    @RequestMapping(value = "/jpa/memberList2", method = RequestMethod.GET)
    public String memberList2(Pageable pageable, Model model) {


        //page기능 만들기
        //spring boot는 페이지 기능을 만들수 있는 클래스를 제공
        //Pageable   페이지 정보를 저장하는 객채
        //Page       검색된 제이터 저장
        Page<Member> members = memberRepository.findAll(pageable);

        System.out.println("전체 페이지 개수=" + members.getTotalPages());
        System.out.println("전체 레코드 개수=" + members.getTotalElements());
        System.out.println("현재 페이지 개수=" + members.getNumber());
        System.out.println("한페이지에 보여주는 글 개수=" + members.getSize());
        System.out.println("정렬=" + members.getSort());
        System.out.println("number,size,sort=" + members.getPageable());


        model.addAttribute("members", members);
        return "jpa/memberList2";
    }

    //jpa/memberList
    @RequestMapping(value = "/jpa/memberList1", method = RequestMethod.GET)
    public String memberList1(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "jpa/memberList";
    }

}
