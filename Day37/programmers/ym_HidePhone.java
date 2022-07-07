package Day37.programmers;

class ym_HidePhone {
    class Solution {
        public String solution(String phone_number) {
            String answer = "";
            String Sb = "";
            for (int i = 0; i < phone_number.length(); i++)
                if (i < phone_number.length()-4) {
                    answer += "*";
                }  System.out.println(answer);

            Sb = phone_number.substring(phone_number.length()-4, phone_number.length());

            answer = answer + Sb;
            return answer;
        }
    }
}
