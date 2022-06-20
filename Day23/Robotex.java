package JAVA1;

public class Robotex
 {
    public static void main(String[] args) {
        Robot myRobot = new Robot();
        
        
       
        System.out.println(myRobot.RobotName);
        System.out.println(myRobot.RobotNum);
        System.out.println(myRobot.Color);
        System.out.println(myRobot.speed);
    
        myRobot.speed = 60;
        myRobot.Color = "black";
        System.out.println("수정된 색상:" + myRobot.Color);
        System.out.println("수정된속도 :" + myRobot.speed);
        
    } 

    
}
