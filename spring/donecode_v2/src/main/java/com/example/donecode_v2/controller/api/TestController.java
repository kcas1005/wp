package com.example.donecode_v2.controller.api;


import com.example.donecode_v2.entity.customDto.CustomAPIDtoExample;
import com.example.donecode_v2.entity.customDto.CustomDtoExample;
import com.example.donecode_v2.entity.customDto.CustomStudentData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//api 데이터 전송 컨트롤로 메서드들은 @responBody 속성 가짐
//@RestController
@Controller
public class TestController {
    //CRUD restFullApi rest하게 API를 만들지 (암묵적인 규칙)
    //Create, Read, Update, Delete
    //data를 전달하는 controller URI주소 만들기

    @ResponseBody
    @RequestMapping("/read/alldata")
    public CustomAPIDtoExample readAlldata(){
        CustomStudentData jskim = new CustomStudentData();

        jskim.setName("김준석");
        jskim.setGroup(1);
        jskim.setPosition("backend");

        return jskim;
    }

    @ResponseBody
    @RequestMapping("/test1")
    public CustomAPIDtoExcample test1(){

        CustomAPIDtoExcample blog = new CustomDtoExample();

    }

}
