package Day42;

public class java32 {
    public static void main(String[] args) {
        Test_2 test_2 = new Test_2();

        try {
            test_2.test_2("1", "ㄱ");
        } catch (NumberFormatException e) {
            System.out.println("오류발생.");
        }
    }
}

class Test_2 {
    public void test_2(String a, String b) throws NumberFormatException {
        try {
            int sum = Integer.parseInt(a) + Integer.parseInt(b);
            System.out.println("문자로 입력받은 " + a + " , " + b + "의 합은 " + sum + "입니다.");
        } catch (NumberFormatException e) {
            System.out.println("숫자형 문자가 아닙니다. 형변환을 할 수가 없습니다.");
            throw e;
        }
    }
}
