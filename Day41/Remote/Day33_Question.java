package Day41.Remote;

import java.util.ArrayList;

//데이터 베이스 관련
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;

import java.sql.DatabaseMetaData;

public class Day33_Question {

    //데이터 베이스 만들기 위한 필드 값
    final static String DB_URL = "jdbc:oracle:thin:@sjhuman_high?TNS_ADMIN=C:\\\\Oracle\\\\Wallet_SJHuman";
    final static String DB_USER = "admin";
    final static String DB_PASSWORD = "System12341234@";
    static ArrayList<String> channal = new ArrayList<>();

    public static void main(String[] arg) throws SQLException {

        /* // 문제 1번
        Searchable sc = new SmartTelevision();

        sc.search("www.naver.com");
        sc.Channel(100); */


        
        /* // 문제 2번
        Remote krm = new Kim();
        Remote prm = new Park();
        Remote lrm = new Lee();

        Remote[] rm = { krm, prm, lrm };

        for (int i = 0; i < 3; i++) {
            rm[i].tv_remote(3);
        }*/


        // 문제 3번
        // 선호 채널, 출력한 채널 저장(배열), 로그인 구현

        //데이터 베이스 연결

        //Statement 객체 생성

        //Query 수행

        //Result 객체로부터 데이터 추출
        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");


        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_URL);
        ods.setConnectionProperties(info);

        try (OracleConnection connection = (OracleConnection) ods.getConnection()) {

            DatabaseMetaData dbmd = connection.getMetaData();
            System.out.println("Driver Name: " + dbmd.getDriverName());
            System.out.println("Driver Version: " + dbmd.getDriverVersion());
            // Print some connection properties
            System.out.println("Default Row Prefetch Value is: " +
                    connection.getDefaultRowPrefetch());
            System.out.println("Database Username is: " + connection.getUserName());
            System.out.println();

            printEmployees(connection);

            System.out.println(channal.toString());

            //Resultset Close

            //Statement 객체 Close

            //Connection 객체 Close


            /*Login lg = new Kim();

            lg.Login("Kim", "Kim123");*/

//로그인 구현    (다형성 이용하기)
// 아이디 입력하라는 출력
// 아이디를 입력  id          스캐너로 비밀먼호 입력
//아이디가
//비밀번호 입력 하라는 출력
// 비밀번호 입력  pw          스캐너로 비밀번호 입력
// 맞는지 확인   confirm     if문
// 아이디 or 비밀번호가 맞습니다 or 틀렸습니다.
// 채널 입력    scanner     스캐너 활용
// 채널 입력 끝  close       채널 끝
// 채널 저장    int[]       배열값 저장
// 가장 많이 입력한 채널 검사  math
// 선호도 채널 출력


            //테스트


            // implements와 extends차이에 따른 사용 예시
            // 인터페이스의 static과 default 불러오는 메모리 구조 방식 차이 설명

            // 문제1. SmartTelevision을 자동 채널 변경 시스템을 Searchable 인터페이스를 통해 구현하시오.
            // ex) 10, 20, 25, 120 채널이 있습니다. 일정 값을 입력받으면
            // 채널에 가장 가까운 번호의 채널로 자동 변경하는 시스템을 구축하시오.

            // 문제2. 김준석, 박준석, 이준석은 리모콘을 각각 갖고 있다.
            // 김준석은 5초마다 채널을 바꾸고, 박준석은 3초마다, 이준석은 11초마다 채널을 바꾸고 싶다.
            // 각 이름의 인터페이스 혹은 부모클래스를 만들고 바꾸고 싶은 채널을 배열에 입력받아 순차적으로 채널이 바뀌는 시스템을 구축하시오.
            // ex) 자바에서 몇 초간 쉬는 내장 메서드가 있습니다.
            // 검색해서 사용하세요.

            // 문제3. 사람마다 선호 채널이 있습니다.
            // 사용했던 (추상클래스)채널의 정보를 저장하는 시스템을 상속 받아 구현하고,
            // SmartTelevision에 (인터페이스)로그인(ID/PW/강제채널변경)하면 선호 채널 중 가장 많이 출력한 채널을 자동으로 바꿔주는 시스템을 구현하시오.
            // (채널 확인하는 부모클래스와 자동으로 채널을 바꿔주는 자식클래스의 형변환을 적용하시오.)

            // 문제4. implements와 extends차이에 따른 사용 예시
            // 문제5. 인터페이스의 static과 default 불러오는 메모리 구조 방식 차이 설명
            // 문제6. 인터페이스는 왜 사용하나? 언제 사용하나?
        }
    }

    public static void printEmployees(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement
                    .executeQuery("select * from SAVEINFO")) {
                System.out.println("id" + "  " + "pw" + "  " + "channel");
                System.out.println("---------------------");
                while (resultSet.next())
                    System.out.println(resultSet.getString(1) + " "
                            + resultSet.getString(2) + " "
                            + resultSet.getString(3) + " ");
                 channal.add(String.valueOf(resultSet.next()));
            }
        }
    }
}