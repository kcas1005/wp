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
public class WeaponBasic {

    public static final String defaultName = "주먹";
    public static final int defaultAttRange = 1;
    public static final int defaultChangeTime = 1;

    String WeaponType = "주먹";
    int Att = 1;
    int AttRange = 1;
    int ChangeTime = 1;

    public void attAction(){
        WeaponType = "주먹";
        Att = 1;
        AttRange = 1;
        ChangeTime = 1;
        System.out.println(WeaponType + "을 장착했습니다.");
        System.out.println("공격력이 " + Att + "올랐습니다.");
        System.out.println("공격범위는 " + AttRange + "입니다.");
        System.out.println("무기 교체시간은 " + ChangeTime + "입니다.");
    }

    public void attAction(String WeaponType, int Att, int AttRange, int ChangeTime) {
        this.WeaponType = WeaponType;
        this.Att = Att;
        this.AttRange = AttRange;
        this.ChangeTime = ChangeTime;
        System.out.println(WeaponType + "을 장착했습니다.");
        System.out.println("공격력이 " + Att + "올랐습니다.");
        System.out.println("공격범위는 " + AttRange + "입니다.");
        System.out.println("무기 교체시간은 " + ChangeTime + "입니다.");
    }

    public void destroyed(){
        WeaponBasic wb = new WeaponBasic();
        System.out.println("무기가 파괴되었습니다.");
        this.WeaponType = wb.WeaponType;
        this.Att = wb.Att;
        this.AttRange = wb.AttRange;
        this.ChangeTime = wb.ChangeTime;
        System.out.println(this.WeaponType + "을 장착했습니다.");
        System.out.println("공격력이 " + this.Att + "올랐습니다.");
        System.out.println("공격범위는 " + this.AttRange + "입니다.");
        System.out.println("무기 교체시간은 " + this.ChangeTime + "입니다.");
    }

}