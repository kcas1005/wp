public class SupersonicAirplane extends Airplane {
    public static final int NORMAL = 1;
    public static final int SUPERSONIC = 2;

    public static int flyMode = NORMAL;

    @Override
    public void fly() {
        if (flyMode == SUPERSONIC) {
            System.out.println("초음속모드 실행");
        } else {
            super.fly();
        }
    }
    public static void main(String[] arg){
    System.out.println("fm: " + fm);
    }
}

public class me{
    int fm = 3;
}