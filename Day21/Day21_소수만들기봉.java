import java.util.*;

class Solution {
public static void main(String[] arg) {
    int[] nums = {1, 2, 3, 4};

    System.out.println(solution(nums)); //출력시 필요

}

public static int solution(int[] nums) {
    ArrayList<Integer> sumNum = new ArrayList<Integer>();
    ArrayList<Integer> total = new ArrayList<Integer>();
//ArrayList = 가변배열 integer 다시 확인

    int answer = 0;
    int count = 0;
   
   
    for(int i = 0; i < nums.length; i++){
    for(int j = i+1; j < nums.length; j++){
    for(int k = j+1; k < nums.length; k++){
        sumNum.add(nums[i] + nums[j] + nums[k]);
          }
       }
    }
//숫자 합
        for(int i = 0; i < sumNum.size(); i++){  // 1은 소수가 아니므로 2부터 시작
            count = 0;
            for(int j = 1; j <= sumNum.get[i]; j++ ){
                if (sumNum.get(i) % j == 0){
                    count ++;
                }
            }
//약수
//
            if(count == 2){
               total.add(sumNum.get(i));
               //소괄호
            }
           
        }
//소수
        answer = total.size();
        return answer;

        }
    }