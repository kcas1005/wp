//수요일
public class Sweetpumpkin_Sandwich extends Sandwich{
    String sweet_pumpkin;

    public void Sweetpumpkin_Sandwich(String bread, String butter,String c){
        super.Sandwich(bread, butter, c);
        sweet_pumpkin = c;
        System.out.println(sweet_pumpkin + "을 집어 넣습니다.");
    }
}