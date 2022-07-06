public class SmartTelevision implements RemoteControl, Searchable {
	private int volume;
	
	public void turnOn() {
		System.out.println("티비가 켜졌습니다.");
	}	
	public void turnOff() {
		System.out.println("티비가 꺼졌습니다.");
	}
	public void setVolume(int volume) {
		if(volume>RemoteControl.MAX_VOLUME) {
			this.volume = RemoteControl.MAX_VOLUME;
		} else if(volume<RemoteControl.MIN_VOLUME) {
			this.volume = RemoteControl.MIN_VOLUME;
		} else {
			this.volume = volume;
		}
		System.out.println("볼륨 : " + this.volume);
	}
	
	public void search(String url) {
		System.out.println(url + "을 찾습니다.");
	}
	public void Channel(int input_channel){
		int min = Integer.MAX_VALUE; // 차이값의 절대값 최소값 저장
		
		int[] arr = {10, 20, 25, 120};
		int target = input_channel; // 해당 데이터와 가장 근접한 값을 찾아야 함
		int answer = 0;
		
		//
		for(int i = 0; i < arr.length; i++) {
			int abs = Math.abs(arr[i] - target); // 차이값의 절대값 반환
			if(abs < min) { // 절대값이 최소값보다 작다면
				min = abs; // 최소값 교체
				answer = arr[i]; // 정답
			}
		}
		System.out.println("입력하신 값: " + input_channel +"에서 가까운 값 " + answer + "번으로 틀겠습니다.");
		

	}
}


/* implements 와 extends의 결정적인 차이는 단일 상속과 다중 상속의 차이라 생각합니다. */
