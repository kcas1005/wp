package Day44.example_20220718__anonymous_implements;

public class AnonymousExample {
	public static void main(String[] args) {
		Anonymous anony = new Anonymous();
		//�͸� ��ü �ʵ� ���
		anony.field.turnOn();
		//�͸� ��ü ���ú��� ���
		anony.method1();
		//�͸� ��ü �Ű��� ���
		anony.method2(
			new RemoteControl() {
				@Override
				public void turnOn() {
					System.out.println("SmartTV�� �մϴ�.");
				}
				@Override
				public void turnOff() {
					System.out.println("SmartTV�� ���ϴ�.");
				}
			}
		);
	}
}
