// 월요일
public  class Blueberry_Sandwich extends Sandwich {
    String blueberry = "블루베리";
    String bacon = "베이컨";
    String c = blueberry;
    String d = bacon;
    
    public void Blueberry_Sandwich( String bread,String butter,String c,String d) {
        super.Sandwich(bread, butter, c ,d);
        blueberry = c;
        System.out.println(blueberry +" 를 집어 넣습니다.");
        bacon = d;
        System.out.println(bacon +" 을 집어 넣습니다.");
        
    }

}
