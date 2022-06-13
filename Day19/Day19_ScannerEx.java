//package 작성
import java.util.Scanner;
//파일명과 클래스명 동일
public class Day19_ScannerEx{
    
    public static void main(String[] args){
        System.out.println("이름, 도시, 나이, 체중, 독신 여부를 빈칸으로 분리하여 입력하세요");
        Scanner sc = new Scanner(System.in);

        String name = sc.next();
        System.out.print("이름은" + name + ", ");

        String city = sc.next();
        System.out.print("도시는" + city + ", ");
        
        int age = sc.nextInt();
        System.out.print("나이는"+ age + ", ");

        double weight = sc.nextDouble();
        System.out.print("체중은" + weight + "kg, ");
        
        boolean isSingle = sc.nextBoolean();
        System.out.print("독신 여부는" + isSingle + "입니다.");

        System.out.println("종합한 결과");
        System.out.println("이름은" + name);
        System.out.println("도시는" + city);
        System.out.println("나이는" + age);
        System.out.println("체중은" + weight + "kg");
        System.out.println("독신 여부는" + isSingle + "입니다.");


        sc.close();
    }
}