import java.util.ArrayList;

public class Day21_소수만들기 {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        C.solution(nums);
        D.solutionB(sum);

    }
}

class C {
    public static int solution(int[] nums) {
        int answer = 0;
        ArrayList<Integer> sum = new ArrayList<>();
        int count = 0;
        for (int a = 0; a < nums.length; a++) {
            for (int b = a + 1; b < nums.length; b++) {
                for (int c = b + 1; c < nums.length; c++) {
                    sum.add(nums[a] + nums[b] + nums[c]);
                    System.out.println("sum: " + sum);
                }
            }
        }
        // D.solutionB(sum);
        return count;
    }
}

class D {
    public static int solutionB(ArrayList<Integer> sum.get()){
        System.out.println("");
        for(int i=0; i<=sum.size(); i++){
            for(int n=0; n<sum.get(i); n++){
            if(sum.get(i)%n==0){

            }
            }
        }
        return sum;
    }
}