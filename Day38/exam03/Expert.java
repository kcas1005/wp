package Day38.exam03;

public class Expert extends Bus implements People{
    public int speed = 60;
    @Override
    public void run() {
        System.out.println("숙련자가 "+ speed +"km로 달립니다.");
    }
    @Override
    public void runaway(){
        System.out.println("숙련자가 "+ speed +"km로 고속도로를 달립니다.");
    }
    @Override
    public void checkFare(){
        System.out.println("숙련자가 얼마나 내었나요?");
    }
    @Override
    public void pay(){
        System.out.println("버스는 요금을 내지않습니다.");
    }
    @Override
    public void enter(){
        System.out.println("숙련자가 HiPass에 입장했습니다.");
    }
    @Override
    public void exit(){
        System.out.println("숙련자가 HiPass를 나갔습니다.");
    }
}