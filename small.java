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
