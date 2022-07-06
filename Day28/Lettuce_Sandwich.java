//화요일
public class Lettuce_Sandwich extends Sandwich{
    String lettuce;
    String bacon;

    public void Lettuce_Sandwich(String bread, String butter, String c, String d){
        super.Sandwich(bread, butter, c, d);
        lettuce = c;
        System.out.println(lettuce + "를 집어 넣습니다.");
        bacon = d;
        System.out.println(bacon + "을 집어 넣습니다.");
    }

}