package Day44.example_20220718__anonymous_implements;

public class Anonymous {
	//�ʵ� �ʱⰪ���� ����
	RemoteControl field = new RemoteControl() {
		@Override
		public void turnOn() {
			System.out.println("TV�� �մϴ�.");
		}
		@Override
		public void turnOff() {
			System.out.println("TV�� ���ϴ�.");
		}
	};
	
	void method1() {
		//���ú��������� ����
		RemoteControl localVar = new RemoteControl() {
			@Override
			public void turnOn() {
				System.out.println("Audio�� �մϴ�.");
			}
			@Override
			public void turnOff() {
				System.out.println("Audio�� ���ϴ�.");
			}
		};
		//���ú��� ���
		localVar.turnOn();
	}
	
	void method2(RemoteControl rc) {
		rc.turnOn();
	}
}











