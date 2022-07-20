package Day42;

public class java30 {

    public static void main(String[] args) {

        try{
            System.out.println("여기는 되지롱");
            throw new Exception();
        } catch(Exception a){
            System.out.println("예외를 강제로 발생했습니다.");
        }
    }
}
