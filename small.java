<<<<<<< HEAD
import java.util.LinkedList;

public class small {

    public static void main(String[] args) {
        int[] num = {1,2,3,4,5};
        int count = 0;
        int result = Is_Prime(num);

        if (result == 2)
            System.out.println(num + "은 소수입니다.");
        else if (result < 2)
            System.out.println("1보다 큰 양의 정수를 입력해주세요.");
        else
            System.out.println(num + "은 소수가 아닙니다.");
    }

    private static int Is_Prime(int[] num) {
        int count = 0;

        LinkedList<Integer> answer = new LinkedList<Integer>();

        for (int i = 1; i <= num.length; i++) {
            for(int a = 1; a <= num[i]; a++){
            if (num[i] % a == 0){
                count += 1;
            }
            if (count >= 3){
            }
            }
        }
        return count;
    }
}
=======
import java.util.ArrayList;

class small {
	public static void main(String[] args) {
		int[] num = {1,2,3,4};

		solutionB(nums);

	public static int solutionA (int[] nums) {
		int answer = 0;

		// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
		System.out.println("Hello Java");

		return answer;
	}
	public static int[] solutionB (int[] nums)

	for(int a=0; a<nums.length; a++){
		for(int b=0; b<nums.length; b++){
			for(int c=0; c<nums.length; c++){

			}
		}
	}
	return null;
	}
}
>>>>>>> 061390d723445ae02edf9aab96e836cc4365e3b8
