public class WeaponLance extends WeaponBasic {
    String WeaponType = "렌스";
    int Att = 5;
    int AttRange = 2;
    int ChangeTime = 5;

    @Override
    public void attAction() {
        WeaponType = "렌스";
        Att = 5;
        AttRange = 2;
        ChangeTime = 5;
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