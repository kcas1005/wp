abstract class House{
    private String roof = "House roof";
    private String wall = "House wall";
    private Door door;

    void setDoor(Door door){
        this.door = door;
    }
    Door getDoor(){
        return door;
    }
    void openDoor(){
        System.out.println("Basic door open");
    }
    void closeDoor(){
        System.out.println("Basic door close");
    }

    
}
interface Door{
    void openDoor();
    void closeDoor();
}

class BasicHouse extends House{
}

class MyHouse extends House{
    @Override
    void openDoor(){
        getDoor().openDoor();

    }
    @Override
    void closeDoor(){
        getDoor().closeDoor();
    }
    
}
class MyDoor implements Door{
    public void openDoor(){
        System.out.println("MyHouse Door open");
    }
    public void closeDoor(){
        System.out.println("MyHouse Door open");
    }
}

public class Abstract{
    public static void main(String[] arg){

        House basicHouse = new BasicHouse();

        basicHouse.openDoor();
        basicHouse.closeDoor();

        House myHouse = new MyHouse();
        Door myDoor = new MyDoor();

        myHouse.setDoor(myDoor);

        myHouse.openDoor();
        myHouse.closeDoor();

    }
}