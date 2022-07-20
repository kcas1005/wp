package Day39.exam03;

public class Master extends People{
    public String name = "전문가 버스";
    public int speed = 100;
    public int maxspeed = 100;
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
        System.out.println(name + "는 얼마나 내었나요?");
    }
    @Override
    public void pay(){
        System.out.println(name + "는 요금을 내지않습니다.");
    }
    @Override
    public void enter(){
        System.out.println(name + "는 HiPass에 입장했습니다.");
    }
//    @Override
    public void exit(){
        System.out.println(name + "는 Hipass를 나갔습니다.");

    }
    public void change(){
        System.out.println(name + "운전자 가 초보운전자로 바뀌었습니다.");
    }
}