import java.util.*;

class cs {

    public static void main(String[] arg) {
        int[] arrarr = { 2, 1, 3, 4, 1 };
        numbers(arrarr);
    }

    ArrayList<Integer> arr = new ArrayList<>();
    int N;

    public int[] solution(int[] numbers) {
        N = numbers.length;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
            }
        }

        int[] answer = new int[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            answer[i] = arr.get(i);

            Arrays.sort(answer);
        }

        return answer;
    }

}