public class WeaponLongLord extends WeaponBasic {
    String WeaponType = "롱로드";
    int Att = 2;
    int AttRange = 1;
    int ChangeTime = 2;

    @Override
    public void attAction() {
        WeaponType = "롱로드";
        Att = 2;
        AttRange = 1;
        ChangeTime = 2;
        System.out.println(WeaponType + "을 장착했습니다.");
        System.out.println("공격력이 " + Att + "올랐습니다.");
        System.out.println("공격범위는 " + AttRange + "입니다.");
        System.out.println("무기 교체시간은 " + ChangeTime + "입니다.");
    }
    @Override
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
    @Override
    public void destroyed(){
        super.destroyed();
    }
}