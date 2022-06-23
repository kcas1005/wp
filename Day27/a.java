public class a {
    static String inField = "a클래스 field에 있는 것 호출했습니다.";

    public static void main(String[] arg) {
        System.out.println("inField: " + inField);

        a_a.inMethod();
    }

}
    class a_a {
        static String ab = "a_a클래스에 있는 Field를 호출 했습니다.";
        public static void inMethod() {
            System.out.println("a클래스에 있는 inMethod를 호출 했습니다");
        }
    }
