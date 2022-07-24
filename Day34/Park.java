package Day34;

public class Park implements Remote{
    public void tv_remote(int input_channel){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("3초 기다림 끝 " + input_channel + "번 화면 출력");
    }
}
