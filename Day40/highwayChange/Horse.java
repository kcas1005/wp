package Day40.highwayChange;

import Day40.highwayChange.driver.*;

public class Horse {
    private Driver driverType;

    public Horse(){
        driverType = new Beginner();
    }
    public void setDriver(Driver driverType){
        this.driverType = driverType;
        System.out.println("드라이버가 교체 되었습니다 : " + driverType.getClass().getName());
    }
    public Driver getDriver(){
        return driverType;
    }
}
