//목요일
public class Eggsalad_Sandwich extends Sandwich{
    String salad;
    String egg;
    
    public void Eggsalad_Sandwich(String bread, String butter, String c, String d){
        super.Sandwich(bread, butter, c, d);
        salad = c;
        System.out.println(salad + "를 집어 넣습니다.");
        egg = d;
<<<<<<< HEAD
        Sandwich(blue);
=======
        System.out.println(egg + "을 집어 넣습니다.");
>>>>>>> 1a559aa20354150b0b84264603d84f4caf7ceaa2
    }
}
