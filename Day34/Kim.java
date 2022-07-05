public class Kim implements Remote, Login {
    String id = "Kim";
    String pw = "Kim123";
    // 오빠 메롱 ^ㅠ^

    public void tv_remote(int input_channel) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("5초 기다림 끝 " + input_channel + "번 화면 출력");
    }

    public void favorit_channel() {

    }

    public void Login(String id, String pw) {
        if (this.id == id) {
            if(this.pw == pw){
                System.out.println("로그인 되었습니다.");
            }
            else{
                System.out.println("비밀번호가 다릅니다.");
            }

        } else {
            System.out.println("Kim의 아이디가 다릅니다.");
        }
    }
}
