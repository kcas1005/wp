import java.util.*;

class Day19_두개뽑아서더하기_봉해답 {
    public static void main(String[] args) {

        int[] numbers = { 5,0,2,7 };
        solution(numbers);
    }

    public static TreeSet<Integer> solution(int[] numbers) {
        TreeSet<Integer> answer = new TreeSet<Integer>();

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                answer.add(numbers[i] + numbers[j]);

            }
        }
        System.out.println(answer);

        return answer;
    }
}