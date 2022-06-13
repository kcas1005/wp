import java.util.ArrayList;

class hello {
    public static void main(String[] args){
        System.out.println("programmers start!");

        // String[] inputText = {"Jane", "Kim"};
        // list String = new list[5];
        ArrayList<String> inputArray = new ArrayList<String>();
        inputArray.add("Jane");
        inputArray.add("Kim");
        System.out.println(solution(inputArray));
    }
    public static String solution(ArrayList<String> inputArray){
        System.out.println(inputArray.size());
        String answer = "";

        String[] array_name =new String[3];
        array_name[0]="Kim";
        array_name[1]="Park";
        array_name[2]="Yi";
        System.out.println(inputArray.size());

        // inputArray 배열을 순회해서 kim의 위치 찾기
        for(int i = 0; i < inputArray.size(); i++){
            /* if(array_name[i].equals("kim")){

            } */
            if(inputArray.get(i) == "Kim"){
                System.out.println("ok");
            }
            //값 비교
            //위치 저장
        }
        StringBuffer text_test = new StringBuffer("helloww");
        text_test.append("abc");
        text_test.append("kkk");
        System.out.println(text_test.length());
        // "김서방은 [배열위치]에 있다."
        // answer = 김서방 + [배열위치] + 에 있다
        return answer;
    }
}
