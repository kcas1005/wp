import java.util.Scanner;

public class review {

    public static void main(String[] args) {
        System.out.println("이름, 도시, 나이, 체중, 독신 여부를 빈칸에 분리하여 입력하세요.");
        Scanner sc = new Scanner(System.in);

        String name = sc.next();
        System.out.println("name: " + name);

        String city = sc.next();
        System.out.println("city: " + city);

        int age = sc.nextInt();
        System.out.println("age: " + age);

        double weight = sc.nextDouble();
        System.out.println("weight: " + weight);

        boolean isSingle = sc.nextBoolean();
        System.out.println("isSingle: " + isSingle);

        sc.close();

    }
}