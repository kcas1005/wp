package Day21;

import java.util.*;

public class Day21_소수만들기 {
    public static void main(String[] args) {
        int[] numbers = { 1, 2, 3, 4 };
        SolutionE.solution(numbers);
    }
}

class SolutionE {
    public static TreeSet<Integer> solution(int[] numbers) {
        TreeSet<Integer> tree = new TreeSet<>();
        int sum = 0;
        int count = 0;
        int answer = 0;
        int length = numbers.length;

        for (int a = 0; a < length; a++) {
            for (int b = a+1; a < length; b++) {
                for (int c = b+1; c < length; c++) {
                    sum = numbers[a] + numbers[b] + numbers[c];
                    tree.add(sum);
                    if(SolutionF.Is_Prime(sum)){

                    }
                }
            }
        }
        return tree;
    }
}

class SolutionF {
    public class small {

        public static int solutionH() {
            int[] num = { 1, 2, 3, 4, 5 };
            int count = 0;
            int result = Is_Prime(num);

            if (result == 2) {
                System.out.println(num + "은 소수입니다.");
            } else if (result < 2) {
                System.out.println("1보다 큰 양의 정수를 입력해주세요.");
            } else {
                System.out.println(num + "은 소수가 아닙니다.");
            }
            return count;
        }

        public static int Is_Prime(int[] num) {
            int count = 0;

            LinkedList<Integer> answer = new LinkedList<Integer>();

            for (int i = 1; i <= num.length; i++) {
                for (int a = 1; a <= num[i]; a++) {
                    if (num[i] % a == 0) {
                        count += 1;
                    }
                    if (count >= 1) {
                    }
                }
            }
            return count;
        }
    }
}
