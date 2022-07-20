package Day38.exam03;

public interface Pay {
    void pay();
default void message(){
    System.out.println("message");
    }
}
