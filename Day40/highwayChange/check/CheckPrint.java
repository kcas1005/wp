package Day40.highwayChange.check;

import Day40.highwayChange.driver.Beginner;
import Day40.highwayChange.driver.Driver;
import Day40.highwayChange.driver.Expert;

public interface CheckPrint {
    final Beginner beginner = new Beginner();
    final Expert expert = new Expert();

    static enum city {천안, 서울};
    static enum highway {고속도로};
    static int getLimitSpeed(Driver input_Driver) {
        if(input_Driver instanceof Expert) {
            return input_Driver.getSpeed();
        }else if(input_Driver instanceof Beginner) {
            return input_Driver.getSpeed();
        }
        return 0;
    }
    static String viewLocation(String location) {
        for (city c : city.values()) {
            if (c.name().equals(location)) {
                return "도시 안 입니다";
            }
        }
        for (highway h : highway.values()) {
            if (h.name().equals(location)) {
                return "고속도로 입니다";
            }
        }
        return "이동 중이 아닙니다";
    }

    static Driver fixDriver(String location) {
        for (city c : city.values()) {
            if (c.name().equals(location)) {
                System.out.println("드라이버 "+beginner.getClass().getName()+" 모드로 변경합니다");
                return beginner;
            }
        }
        for (highway h : highway.values()) {
            if (h.name().equals(location)) {
                System.out.println("드라이버 "+expert.getClass().getName()+" 모드로 변경합니다");
                return expert;
            }
        }
        System.out.println("알 수 없는 위치로 "+beginner.getClass().getName()+" 모드로 변경합니다");
        return beginner;
    }
}
