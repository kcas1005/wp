package com.example.donecode_v2;

import com.example.donecode_v2.entity.account.Member;
import com.example.donecode_v2.service.apiTest.apiTest;
import com.example.donecode_v2.service.textTransfer.TextTransfer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.krb5.internal.cache.MemoryCredentialsCache;

@SpringBootTest
class LectureSpring2CrudProjectApplicationTests {


    @Autowired
    TextTransfer textTransfer;

    @Test
    void contextLoads() {
    }

    @Autowired
    apiTest apitest_1;

    @Test
    void apiTest_2(){
       apitest_1.resultAPI();
    }
    @Test
    @DisplayName("저장, 데이터가 잘 들어갔는지 확인")
    void contextsave(){
        //Stter로 엔티티를 생성하고 repository가 정상 작동하는지 확인
        Member member = new Member();
        //클라이언트에서 controller에 데이터를 전달하는 내용을 setter를 통해 대체
        member.setId("humanClass4");
        member.setPassword("12341234@");
        member.getEmail("class4@123.com");
        memberRepository.save(member);
    }




    @Test
    void textTest() throws Exception{

    }
    @Test
    void Scraping(){
        selenium.scraping();
    }
    @Test
    void ScrapingTest(){
        seleniumExample.scraping();
    }

}
