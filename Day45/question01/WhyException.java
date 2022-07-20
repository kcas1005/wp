package Day45.question01;

public class WhyException extends Exception {
    private int exceptuonCount = 0;

    public WhyException(){

    }
    public WhyException(String message){
        super(message);
        exceptuonCount ++;
    }
}
