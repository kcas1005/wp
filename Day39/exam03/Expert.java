package Day39.exam03;

public class Expert extends People{
    // 달리는 스피드
    public String name = "숙련자 버스";
    public int speed = 60;
    public int maxspeed = 60;
    @Override
    public void run() {
        if(maxspeed >= speed) {
            System.out.println( name + "가 " + speed + "km로 달립니다.");
        }else if(maxspeed < speed) {
            System.out.println(name + "가 " + maxspeed + "km로 달립니다.");
        }
    }
    @Override
    public void runaway(){
        if(maxspeed >= speed) {
            System.out.println(name + "가 " + speed +"km로 고속도로를 달립니다.");
        }
        else if(maxspeed > speed) {
            System.out.println(name + "가 " + speed +"km로 고속도로를 달립니다.");
        }
    }
    @Override
    public void checkFare(){
        System.out.println(name + "는 얼마나 내었나요?");
    }
    @Override
    public void pay(){
        System.out.println(name + "는 요금을 내지않습니다.");
    }
    @Override
    public void enter(){
        System.out.println(name + "는 HiPass에 입장하지 못합니다.");
    }
//    @Override
    public void exit(){
        System.out.println(name + "는 Hipass를 들어온 적이 없습니다.");
    }
}