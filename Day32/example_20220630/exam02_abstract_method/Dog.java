package example_20220630.exam02_abstract_method;

public class Dog extends Animal {
	public Dog() {
		this.kind = "포유류";
		// super.check;
	}

	@Override
	public void sound() {
		System.out.println("멍멍");
	}
}