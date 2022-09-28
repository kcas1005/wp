package com.example.donecode_v2.service.apiTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//@Service : 컨테이너에 bean을 등록하여 controller가 불러 올 수 있도록 선언
@Service
public class apiTest {

    //메서드마다 기능은 꼭 하나씩만 하도록 규칙 SOLID
    public void resultAPI(){
        String result = readAPI();

        //파싱받아서 가공하는 라이브러리 JAVA
        //1. SimpleJson : 대용량 데이터 처리 속도가 빠름
        //2. Jackson : 평균적으로 빠름
        //3. Gson : 간단한 데이터 처리 속도가 빠름(구글에서 만듬)

        Gson resultGson = new GsonBuilder().setPrettyPrinting().create();
        String element = resultGson.toJson(result);

        System.out.println(element);

    }

    public String readAPI() {
        String key = "Z7SbVkdy722kB991dddaJs78O2oev6n1ptwfeqIAZXhiOSaIuWGjs3kM82c9iNccqmD%2Fmgz%2F55POvL0YMXqZFQ%3D%3D";

        //데이터를 파싱받을 변수
        String brResult = "";
        //데이터를 받아와서 String 객체로 만들기 전에 한줄씩 더해야 하므로, StringBuilder사용
        StringBuilder sb = new StringBuilder();

            //JSON API라는 것은 네트워크 통신을 통해 데이터를 다운받아, 서비스 할 수 있도록 가공하기 위한 데이터
            //네트워크 통신이 끊기거나 예외적인 상황을 상정
            try {
                //https는 인증서가 필요하므로 http로 데이터 다운
                URL url = new URL("http://apis.data.go.kr/B551177/BusInformation/getBusInfo?serviceKey=Z7SbVkdy722kB991dddaJs78O2oev6n1ptwfeqIAZXhiOSaIuWGjs3kM82c9iNccqmD%2Fmgz%2F55POvL0YMXqZFQ%3D%3D&numOfRows=10&pageNo=1&area=1&type=json");

    //            인증서가 필요한 객체
    //            HttpsURLConnection
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedReader br =
                        new BufferedReader
                                (new InputStreamReader(con.getInputStream(), "UTF-8"));

                while ((brResult = br.readLine()) != null) {
                    sb.append(brResult);
                    System.out.println(brResult);
                }

                br.close();
                con.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
    }