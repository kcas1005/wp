public class Day31_Test {

    public static void main(String[] arg){
        Eating t_eating = new Eating();
        Eat t_eat = new Eat();
        Icecream t_icecream = new Icecream();
        Noodle t_noodle = new Noodle();

        t_eat.rice(t_eating);
        t_eat.rice(t_icecream);
        t_eat.rice(t_noodle);

        Noodle t_eat_A = t_noodle;
        method1(t_eat_A);
        method2(t_eat_A); //예외 발생

        // t_eat_A.goicecream();
        t_eat_A.gonoodle();

        // Eat t_eat_B = new Eat();
        // method1(t_eat_B);
    }
    public static void method1(Eating parent){
        if(parent instanceof Icecream){
            Icecream child = (Icecream) parent;
            System.out.println("method1 - Child로 변환 성공");
        }
        else {
            System.out.println("method1 - Child로 변환되지 않음");
        }
        
    }
    public static void method2(Eating parent){
        Eating child = (Noodle)parent;
        System.out.println("method2 - Child로 변환 성공");
    }
    
}
