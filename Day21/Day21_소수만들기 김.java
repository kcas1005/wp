class Solution {
    public int solution(int[] nums) {
       int answer = 0;
        // nums에 숫자가 3개 있어서 for문 3번 돌림
        for (int i = 0; i < nums.length - 2; i++) { 
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j]+ nums[k];
                    if(bool(sum)){
                        answer++;
                    }
                }
            }
             
        }
    
        return answer;
    }
    
    public static boolean bool(int num){
        for(int i =2; i<num; i++){
            if(num%i==0) return false;
        }
        return true;
    }
    

}