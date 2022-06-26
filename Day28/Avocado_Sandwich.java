//금요일
public class Avocado_Sandwich extends Sandwich{
    String egg = "계란";
    String avocado = "아보카도";
    
    public void Avocado_Sandwich(String bread, String butter, String c, String d){
        super.Sandwich(bread, butter, c, d);
        avocado = c;
        System.out.println(avocado + "를 집어 넣습니다.");
        egg = d;
        System.out.println(egg + "을 집어 넣습니다.");


    }
}
