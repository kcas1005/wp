package Day41.Remote;

public interface Login {

    final Kim kim = new Kim();

//    String id = "";
//    String pw = "";

    public default void Login(String id, String pw) {
        if (id.equals(Kim.id) && (pw.equals(Kim.pw))) {
            System.out.println(kim.id + "이/가 로그인 되었습니다.");
        } else {
            System.out.println("아이디 또는 비밀번호가 다릅니다.");
        }
    }
}


