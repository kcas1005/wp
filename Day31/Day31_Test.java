public class Day31_Test {

    public static void main(String[] arg){
        Eating t_eating = new Eating();
        Eat t_eat = new Eat();
        Icecream t_icecream = new Icecream();
        Noodle t_noodle = new Noodle();

        t_eating.eeating(t_eat);
        t_eating.eeating(t_icecream);
        t_eating.eeating(t_noodle);

        // Eat t_eat_A = new Eat();
        // method1(t_eat_A);
        // method2(t_eat_A); //예외 발생

        // Eat t_eat_B = new Eat();
        // method1(t_eat_B);
    }
    public static void method1(Eat parent){
        if(parent instanceof Icecream){
            Icecream child = (Icecream) parent;
            System.out.println("method1 - Child로 변환 성공");
        }
        else {
            System.out.println("method1 - Child로 변환되지 않음");
        }
        
    }
    public static void method2(Eat parent){
        Icecream child = (Icecream) parent;
        System.out.println("method2 - Child로 변환 성공");
    }
    
}
