import java.util.*;

public class rantest{
    public static void main(String[] args){
        double random = Math.random();
        int intValue = (int)(random * 11)+ 0;
        if(intValue > 2){
            System.out.println("공격 성공");
        }else{
            System.out.println("공격 실패");
        }
    }

}