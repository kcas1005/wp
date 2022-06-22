package slimeLand;

public class tigerM extends slime{
    public tigerM(String name, int hp, int mp, int hit){
        super(name, hp, mp, hit);
        this.hp = hp *40;
 
    }

    
    public int hit() {
        double random = Math.random();
                            //(이게 랜덤 공격 * 숫자 = 데미지는 늘려주지 최대, 최소값 바뀜) + 0 (최소값)
        int intValue = (int) (random * 301) + 100;
        if (intValue > 130) {
            System.out.println(intValue + "공격에 성공했습니다.");
        } else {
            System.out.println("공격에 실패했습니다.");
            intValue = 0;
        }

        return intValue;
    }

    public void dmg(int dmg) {
        this.hp = this.hp - dmg;
        if (dmg > 0) {
            System.out.println(this.name + "은 " + dmg + "의 공격을 받았습니다.");
        } else {

        }

    }

    public void info() {
        System.out.println("__________" + name + "__________");
        System.out.println("Name = " + this.name);
        System.out.println("HP = " + this.hp);
        System.out.println("MP = " + this.mp);
    }

    
    // 죽을 경우에 드랍
    public void die() {
        if (this.hp <= 0) {
            System.out.println(this.name + "이/가 죽었습니다.");
            this.hp=0;
        } else {
            System.out.println("없음");

        }
    }
}
