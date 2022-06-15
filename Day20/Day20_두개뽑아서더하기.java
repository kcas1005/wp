import java.util.Arrays;
import java.util.LinkedHashSet;

public class Day19_두개뽑아서더하기 {
    public static void main(String[] args) {
        int[] a = { 5,0,2,7,1,8 };
        LinkedHashSet<Integer> b = new LinkedHashSet<Integer>();
        int length = a.length;
        System.out.println("a: " + length);
        System.out.println(Arrays.toString(a));

        for (int x = 2; x < 13; x++) {
            for (int y = 0; y < length; y++) {
                for (int z = y ; z < length; z++) {
                    if ((a[y] + a[z] == x) && (y!=z)) {
                        b.add(x);
                    }
                }
            }

        }
        System.out.println(b);
    }
}

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        return answer;
    }
}

// 순서 오름차순 int형으로
// a[x] = a[x]가 나오면 안됨
// a[x] == a[x+1]+a[y]
