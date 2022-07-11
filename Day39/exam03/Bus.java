package Day39.exam03;

public class Bus implements Vehicle, Pay, Hipass {
	public int speed;
	@Override
	public void run() {
		System.out.println("일반버스가 달립니다.");
	}
	public void runaway(){
		System.out.println("일반버스가 고속도로를 달립니다.");
	}
	public void checkFare(){
		System.out.println("얼마나 내었나요?");
	}
	public void pay(){
		System.out.println("일반버스는 입장하지 않기 때문에 요금을 내지않습니다.");
	}
	public void enter(){
		System.out.println("일반버스는 HiPass에 입장하지 못합니다.");
	}
	public void exit(){
			System.out.println("일반버스는 Hipass를 들어온 적이 없습니다.");
		}
	}
