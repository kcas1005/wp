public class WeaponShortSword extends WeaponBasic {
    String WeaponType = "숏소드";
    int Att = 3;
    int AttRange = 1;
    int ChangeTime = 3;

    @Override
    public void attAction() {
        WeaponType = "숏소드";
        Att = 3;
        AttRange = 1;
        ChangeTime = 3;
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