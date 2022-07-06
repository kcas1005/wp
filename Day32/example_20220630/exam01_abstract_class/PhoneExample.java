package example_20220630.exam01_abstract_class;

public class PhoneExample {
	public static void main(String[] args) {
		//Phone phone = new Phone(); (x)
		
		Phone smartPhone = new SmartPhone("홍길동");
		
		smartPhone.turnOn();
		// smartPhone.internetSearch();
		smartPhone.turnOff();
		System.out.println("------------------");
	}
}
