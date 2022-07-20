package Day45.sec07_user_define_exception;

public class BalanceInsufficientException extends Exception {
    private int exceptuonCount = 0;

    public BalanceInsufficientException() {
    }

    /*	public BalanceInsufficientException(int checkInt){
        }*/
    public BalanceInsufficientException(String message) {
        super(message);
        exceptuonCount ++;

    }
}

