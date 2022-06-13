//package 작성
import java.util.Scanner;
//파일명과 클래스명 동일
public class Day19_confirm{
    
    public static void main(String[] args){
        System.out.println("오늘 무슨 날인지 입력하세요.");
        Scanner sc = new Scanner(System.in);
        
        
        int day = sc.nextInt();
        int day1 = day - 1;
        int day4 = day - 3;
        int day7 = day - 6;
        int day15 = day - 13;
        int day30 = day - 29;
        if(day1 < 0){
            System.out.println("복습할 내용이 없습니다.");
        }
        else if(day4 < 0){
            System.out.println("복습: " + day1 +" day");
        }
        else if(day7 < 0){
            System.out.println("복습: " + day1 +" day");
            System.out.println("복습: " + day4 +" day");
        }
        else if(day15 < 0){
            System.out.println("복습: " + day1 +" day");
            System.out.println("복습: " + day4 +" day");
            System.out.println("복습: " + day7 +" day");
        }
        else if(day30 < 0)        {
            System.out.println("복습: " + day1 +" day");
            System.out.println("복습: " + day4 +" day");
            System.out.println("복습: " + day7 +" day");
            System.out.println("복습: " + day15 +" day");
        }
        else{
            System.out.println("복습: " + day1 +" day");
            System.out.println("복습: " + day4 +" day");
            System.out.println("복습: " + day7 +" day");
            System.out.println("복습: " + day15 +" day");
            System.out.println("복습: " + day30 +" day");
        }


        
        
        
    }
}