package Day37.programmers;
/*문제설명
프로그래머스 모바일은 개인정보 보호를 위해 고지서를 보낼 때 고객들의 전화번호의 일부를 가립니다.
전화번호가 문자열 phone_number로 주어졌을 때,
전화번호의 뒷 4자리를 제외한 나머지 숫자를 전부 *으로 가린 문자열을 리턴하는 함수, solution을 완성해주세요.*/

public class sj_HidePhone {
        static String phone_number;
    public static void main(String[] args) {
        sj_HidePhone hp = new sj_HidePhone();
        hp.solution("01099881505");


    }
    public String solution(String phone_number) {
        int index = phone_number.length();
        int testa = index-4;
        String testb ="";
        String answer="";

        String newphone_number = phone_number.substring(testa,index);

        for(int i=0; i < testa; i++ ){
            testb += "*" ;
        }

        answer = testb+newphone_number;
        return answer;
    }
   /* public void test(){
        String phone_number = "01099881505";
        int index = phone_number.length();
        int testa = index -4;
        String testb ="";
        String answer="";

        String newphone_number = phone_number.substring(index-4,index);

        for(int i=0; i < testa; i++ ){
            testb += "*" ;
        }

        answer = testb+newphone_number;
        return answer;
    }*/

}
