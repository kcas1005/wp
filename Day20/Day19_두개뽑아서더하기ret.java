import java.util.*;

class Day19_두개뽑아서더하기ret {
    public static void main(String[] args) {
        int[] numbers = { 5, 0, 2, 7 };
        a.solution(numbers);
    }

}

class a {
    public static TreeSet<Integer> solution(int[] numbers) {
        TreeSet<Integer> answer = new TreeSet<Integer>();
        int length = numbers.length;
        // System.out.println("numbers.length: " + length);
        // System.out.println(Arrays.toString(numbers));
        for (int y = 0; y < length; y++) {
            for (int z = y; z < length; z++) {
                if (y!=z) {
                    answer.add(numbers[y] + numbers[z]);
                }
            }

        }
        System.out.println(answer);
        return answer;
    }
}
