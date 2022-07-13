package Day41.Remote;

public interface Login {
    String id = "";
    String pw = "";
    public static void Login(String id, String pw) {
        if (id == id) {
            if(this.pw == pw){
                System.out.println("로그인 되었습니다.");
                //선호하는 채널 정보
            }
            else{
                System.out.println("비밀번호가 다릅니다.");
            }

        } else {
            System.out.println("Kim의 아이디가 다릅니다.");
        }
    }
}

// 아이디를 입력  id
// 비밀번호 입력  pw
// 맞는지 확인   confirm
// 채널 입력    scanner
// 채널 입력 끝  close
// 채널 저장    int[]
// 가장 많이 입력한 채널 검사 math
// 선호도 채널 출력
