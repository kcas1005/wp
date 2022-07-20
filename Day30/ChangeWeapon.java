import java.util.*;

// 용사는 슬라임을 잡기 위해 무기 교체 시스템을 개발하려고 합니다.
// 상속과 오버라이딩을 사용하여,
// 주먹,숏소드,롱로드,랜스 4가지의 무기타입을 교체합니다.
// 필드명 AttRange, ChangeTime 으로 무기교체 시간과 공격 범위를 상속받고
// 메서드 attAction으로 이벤트를 발생 시키시오.

// 어려운 문제
// 용사와 슬라임 3마리가 자동으로 싸우며 랜덤으로 무기를 교체하고 효율적으로
// 자동전투를 할 수 있는 시스템을 개발하시오.

// 다 만드신 분들은 포션의 개수와 공격력 랜덤 출력하는 게임시스템을 추가하여
// 용사 1턴 마다 효율적인 공격방식을 재구성하는 알고리즘을 만드시오.
// 유클리드

// 용사 입장에서 슬라임의 수를 확인한다.

// 용사 입장에서 가지고 있는 무기 타입의 공격범위와 공격력을 확인한다.

// 용사입장에서 가지고 있는 무기 타입을 교체하는 시간까지 고려하여 연산한다.

// 효율적인 방식을 선택하여 진행한다.
public class ChangeWeapon {
    public static void main(String[] arg) {
        // 객체 생성
        Slime sl = new Slime();
        Fighter ft = new Fighter();

        // 슬라임 생성
        // sl.slime( sl.Name, sl.hp, sl.mp, sl.att, sl.AttRange);
        // sl.slime();
        // sl.slime("약한 슬라임", 1, 0, 1, 2);
        // sl.slime("강한 슬라임", 50, 0, 5, 2);
        // 랜덤 슬라임 생성
       /*  sl.create();
        // 용사 생성
        ft.Fighter();
        
        // 슬라임 정보창
        sl.info();
        // 용사 정보창
        ft.info();

        while ((sl.hp > 0) && (ft.hp > 0) == true) {
            // 슬라임 죽었는지 확인
            sl.die();
            // 용사 죽었는지 확인
            ft.die();
            
            // 용사가 공격해서 슬라임 데미지 받음
            sl.dmg(ft.att());

            // 슬라임이 공격해서 용사 데미지 받음
            ft.dmg(sl.att());
        } */
        Timer m_timer = new Timer();
        TimerTask m_task = new TimerTask() {
            
            @Override
            public void run(){
                System.out.println("Morph");
                System.out.println("Morph2");
            }
        };

        m_timer.schedule(m_task, 5000);
    

    }
}