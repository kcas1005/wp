class Bus {

    private TestDoor testdoor;
    
    void SetDoor(TestDoor testdoor){
        this.testdoor = testdoor;
    }
    TestDoor GetDoor(){
        return testdoor;
    }

    void OpenDoor() {
        System.out.println("문이 열렸습니다.");
    }

    void CloseDoor(){
        System.out.println("문이 닫혔습니다.");
    }
}

interface TestDoor{
    void OpenDoor();
    void CloseDoor();
}

class BasicBus extends Bus{

}
class MyBus extends Bus{
    
    @Override
    void OpenDoor(){
        GetDoor().OpenDoor();
    }

    @Override
    void CloseDoor(){
        GetDoor().CloseDoor();
    }

}

abstract class TestMyDoor implements TestDoor{
    public void OpenDoor(){
        System.out.println("마이버스 문이 열렸습니다.");
    }

    public void CloseDoor(){
        System.out.println("마이버스 문이 닫혔습니다.");
    }

}

public class Test{
    public static void main(String[] arg){
        Bus bbus = new BasicBus();

        bbus.OpenDoor();
        bbus.CloseDoor();

        Bus mybus = new MyBus();
        TestDoor testdoor = new TestMyDoor(){};

        mybus.SetDoor(testdoor);
        
        mybus.OpenDoor();
        mybus.CloseDoor();
    
    }

}