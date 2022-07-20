package Day38.exam03;

public class Taxi implements Vehicle, Pay, Hipass {
	@Override
	public void run() {
		System.out.println("택시가 달립니다.");
	}
	public void runaway(){
		System.out.println("택시가 고속도로를 달립니다.");
	};
	public void checkFare(){
		System.out.println("택시 요금이 얼마나 내었나요?");
	}
	public void pay(){

	};
	public void enter(){};
	public void exit(){};
}