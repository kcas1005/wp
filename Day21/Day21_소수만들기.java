public class Day21_소수만들기 {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        C.solution(nums);
    }
}

class C {
    public static int solution(int[] nums) {
        int answer = 0;
        int sum = 0;
        int count = 0;
        for (int a = 0; a < nums.length; a++) {
            for (int b = a + 1; a < nums.length; b++) {
                for (int c = b + 1; c < nums.length; c++) {
                    for (int d = 1; d < nums[a]+nums[b]+nums[c]; d++) {
                        if ((a != b) && (a != c) && (b != c)) {
                            if (nums[a] + nums[b] + nums[c] % d == 0) {

                                sum = nums[a] + nums[b] + nums[c];
                                System.out.println("sum: " + sum);
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}
