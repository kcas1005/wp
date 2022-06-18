package Day22;

import java.util.*;

class 자물쇠와열쇠 {
    public static void main(String[] args) {
        int[][] keyValue = {
                { 0, 0, 0 },
                { 1, 0, 0 },
                { 0, 1, 1 } };
        int[][] lockValue = {
                { 1, 1, 1 },
                { 1, 1, 0 },
                { 1, 0, 1 } };
        System.out.println("확인용");
        up(keyValue);
        System.out.println("메인 keyValue: " + keyValue);
    }

    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        return answer;
    }

    public static int[] lock(int[][] lockValue) {
        return null;
    }

    public static int[] Key(int[][] KeyValue) {
        return null;
    }

    public static int[] degree(int[][] keyValue) {
        return null;
    }

    public static int[][] up(int[][] keyValue) {
        
        for (int i = 0; i < keyValue.length; i++) {
            int[][] keyValueUp = new int[][]{
                { 0, 0, 0 },
                { 1, 0, 0 },
                { 0, 1, 1 } };
            keyValue[i]=keyValueUp[keyValueUp.length-i];
            keyValue[i]=keyValueUp[keyValueUp.length-i];
            keyValue[i]=keyValueUp[keyValueUp.length-i];
            
            System.out.println("keyValueUp: " + keyValueUp);
        }
        System.out.println("keyValue: " + keyValue);
        return keyValue;
    }

    public static int[] down(int[][] keyValue) {
        return null;
    }

    public static int[] right(int[][] keyValue) {
        return null;
    }

    public static int[] left(int[][] keyValue) {
        return null;
    }

    public static int[] chase(int[][] keyValue) {
        return null;
    }

    public static int[] Search(int[][] lockValue) {
        return null;
    }

    public static int[] keyLock(int[][] keyValue) {
        return null;
    }

    public static int[] matching(int[][] keyValue) {
        return null;
    }
}
