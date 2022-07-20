package Day39.exam03;

public class Novice extends People{
    public String name = "초보자 버스";
    public int speed = 30;
    public int maxspeed = 30;
    @Override
    public void run() {
        System.out.println(name + "가 "+ speed +"km로 달립니다.");
    }
    @Override
    public void runaway(){
        System.out.println(name + "가 "+ speed +"km로 고속도로를 달립니다.");
    }
    @Override
    public void checkFare(){
        System.out.println(name + "가 얼마나 내었나요?");
    }
    @Override
    public void pay(){
        System.out.println(name + "는 요금을 내지않습니다.");
    }
    @Override
    public void enter(){
        System.out.println(name + "는 HiPass에 입장하지 못합니다.");
    }
    @Override
    public void exit(){
        System.out.println(name + "는 Hipass를 들어온 적이 없습니다.");
    }
}