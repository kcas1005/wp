public class Day21_num {
    public static void main(String[] args) {
        Number number1 = new Number();
        Number number2 = new Number();

        number1.numStatic++;
        number1.numNormal++;
        System.out println("number1.numStatic: " + number1.numStatic);
        System.out.println("number1.numNormal: " + number1.numNormal);

        System.out.println("number2.numStatic: " + number2.numStatic);
        System.out.println("number2.numNormal: " + number2.numNormal);
        
        System.out.println("number1.numNormal: " + number1.numNormal);
    }
}

class Number {
    static int numStatic = 10;
    int numNormal = 10;

}