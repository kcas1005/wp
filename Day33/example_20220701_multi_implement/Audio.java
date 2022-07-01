package example_20220701_multi_implement;

public class Audio implements RemoteControl {
    private int volume;

    public void turnOn(){
        System.out.println("Audio 전원이 꺼졌습니다.");
    };
	public void turnOff(){
        System.out.println("Audio 전원이 켜졌습니다.");

    };
	public void setVolume(int volume){
        
        if(volume>RemoteControl.MAX_VOLUME) {
			this.volume = RemoteControl.MAX_VOLUME;
		} else if(volume<RemoteControl.MIN_VOLUME) {
			this.volume = RemoteControl.MIN_VOLUME;
		} else {
			this.volume = volume;
		}
		System.out.println("볼륨 : " + this.volume);

    };

}
