package Day38.exam03;

public class Bus implements Vehicle, Pay, Hipass {
	public int speed;
	@Override
	public void run() {
		System.out.println("버스가 달립니다.");
	}
	public void runaway(){
		System.out.println("버스가 고속도로를 달립니다.");
	}
	public void checkFare(){
		System.out.println("얼마나 내었나요?");
	}
	public void pay(){
		System.out.println("버스는 요금을 내지않습니다.");
	}
	public void enter(){
		System.out.println("HiPass에 입장했습니다.");
	}
	public void exit(){
		System.out.println("HiPass를 나갔습니다.");
	}
}