import java.util.Arrays;

public class point {

    public static void main(String[] arg) {

        /*
         * // 사각형 별찍기
         * for (int i = 0; i < 10; i++) {
         * for (int j = 0; j < 10; j++) {
         * System.out.print("*");
         * }
         * System.out.println("");
         * }
         */

        /*
         * // 직각삼각형 만들기
         * for (int i = 0; i < 5; i++) {
         * for (int j = 0; j < i; j++) {
         * System.out.print("*");
         * }
         * System.out.println("");
         * }
         */

        /*
         * // 역 직각삼각형 만들기
         * for (int i = 5; i > 0; i--) {
         * for (int j = 0; j < i; j++) {
         * System.out.print("*");
         * }
         * System.out.println("");
         * }
         */

        /*
         * // 일반 삼각형
         * for (int i = 1; i < 10; i += 2) {
         * for (int j = 0; j < i; j++) {
         * System.out.print("*");
         * }
         * System.out.println("");
         * }
         */

        /*
         * // 역 일반 삼각형
         * for (int i = 11; i > 0; i-=2) {
         * for (int j = 0; j < i; j++) {
         * System.out.print("*");
         * }
         * System.out.println("");
         * }
         */

        // String a = " ";
        // String b = " ";

        /*
         * for (int i = 11; i > 0; i -= 2) {
         * for (int k = 0; k < i; k += 1) {
         * System.out.print("*");
         * }
         * System.out.println("");
         * }
         */

        // String c = "*";
        // String d = "*";

        /* // 마름모
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i; j--) {
                System.out.print(" ");
            }
            for (int k = 1-i; k < i; k++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        for(int i = 2; i < 5; i++){
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int k = 9-i; k > i; k--) {
                System.out.print("*");
            }
            System.out.println("");
        }
    } */
    원형


    // ☆☆★☆☆
    // ☆★★★☆
    // ★★★★★

    // ★☆☆☆☆
    // ★★☆☆☆
    // ★★★☆☆
    // ★★★★☆
    // ★★★★★

    /*
     * //배열로 사각형
     * String[][] x = new String[60][30];
     * for(int i=0; i<x.length; i++){
     * for(int j=0; j<x[i].length; j++){
     * x[i][j] = "*";
     * }
     * }
     * 
     * for(int i=50; i<x.length; i++){
     * for(int j=10; j<x[i].length; j++){
     * x[i][j] = "@";
     * }
     * }
     * 
     * for(int i=0; i<x.length; i++){
     * for(int j=0; j<x[i].length; j++){
     * System.out.print(x[i][j]);
     * }
     * System.out.println("");
     * }
     * // System.out.println(Arrays.deepToString(x));
     */

}

// 마름모

// 원형

// 어려운 문제. 얼굴 그리기
