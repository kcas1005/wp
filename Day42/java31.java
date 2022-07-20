package Day42;

import java.sql.SQLException;

public class java31 {
    public static void main(String[] args) {

        Test test = new Test();

        /*try {
            test.test("1", "ㄱ");

        } catch (NumberFormatException e) {
            System.out.println("입력하신 값은 숫자가 아닙니다.");
        } catch (Exception e) {
            System.out.println("강제 오류 만듬.");
        }*/
    }
}

class Test {
    public void test(String a, String b) throws Exception {
        throw new Exception();
//        int sum = Integer.parseInt(a) + Integer.parseInt(b);
//        System.out.println("문자로 받은 값은 " + a + " , " + b + "의 합은 입니다.");
    }
}
