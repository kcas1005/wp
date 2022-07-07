package Day37.Remote;

import java.util.concurrent.TimeUnit;

public class Lee implements Remote {
    //스레드슬립
    /*public void tv_remote(int input_channel){
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("11초 기다림 끝 " + input_channel + "번 화면 출력");
    }*/

    //타임유닛슬립
    public void tv_remote(int input_channel) {
        try {
            for(int i = 1; i < 12; i++){
                TimeUnit.SECONDS.sleep(2);
                System.out.println(i + "초 경과");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e);;
        }
        System.out.println("11초 기다림 끝 " + input_channel + "번 화면 출력");
    }
}
