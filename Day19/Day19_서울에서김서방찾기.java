import java.util.ArrayList;

class Solution {
    public static void main(String[] arg) {
        // String 고정배열, [배열번호]로 추가, length속성으로 크기 측정
        String[] seoul = new String[3];
        // String[] seoul = { "Jane", "Kim", "none" };
        seoul[0] = "Jane";
        seoul[1] = "Kim";
        seoul[1] = "none";

        System.out.println(solution(seoul));

        // StringBuffer는 가변배열, append()메서드로 추가, length()메서드로 크기 측정
        StringBuffer text_test = new StringBuffer();
        text_test.append("oneStep");
        text_test.append("TwoStep");
        System.out.println(text_test.length());
    }

    public static String solution(String[] seoul) {
        String answer = "";
        // ArrayList 가변배열, add()메서드로 추가, size()메서드로 크기 측정
        ArrayList<String> inputArray = new ArrayList<String>();
        inputArray.add("Jane");
        inputArray.add("Kim");
        System.out.println(inputArray.size());
        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i].equals("Kim")) {
                answer = "김서방은 " + i + "에 있다";
            }
        }

        return answer;
    }
}