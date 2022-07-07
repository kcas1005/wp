package Day37.programmers;

public class sh_Average {
    class Solution {
        public double solution(int[] arr) {
            double answer = 0;
            double answer1 = 0;

            for(int i = 0 ; i < arr.length;  i++) {
                answer1 += arr[i];
                answer = answer1/ arr.length ;
            }


            return answer;
        }
    }
}
