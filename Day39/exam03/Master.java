package Day39.exam03;

public class Master extends People{
    public int speed = 100;
    @Override
    public void run() {
        System.out.println("전문가 버스가 "+ speed +"km로 달립니다.");
    }
    @Override
    public void runaway(){
        System.out.println("전문가 버스가 "+ speed +"km로 고속도로를 달립니다.");
    }
    @Override
    public void checkFare(){
        System.out.println("전문가 버스는 얼마나 내었나요?");
    }
    @Override
    public void pay(){
        System.out.println("전문가 버스는 요금을 내지않습니다.");
    }
    @Override
    public void enter(){
        System.out.println("전문가 버스는 HiPass에 입장했습니다.");
    }
//    @Override
    public void exit(){
        System.out.println("전문가 버스는 Hipass를 나갔습니다.");

    }
    public void change(){
        System.out.println("운전자가 초보운전자로 바뀌었습니다.");
    }
}