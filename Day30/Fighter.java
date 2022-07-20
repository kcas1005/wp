import java.util.*;

public class Fighter {
    // 용사 기본필드값
    String Name = "평범한 용사";
    int hp = 300;
    int mp = 100;
    int att = 5;
    int AttRange = 1;

    // 기본 용사 만들 때
    void Fighter() {
        this.Name = "평범한 용사";
        this.hp = 300;
        this.mp = 100;
        this.att = 1;
        this.AttRange = 1;
    }

    // 내가 원하는 용사 만들 때
    void Fighter(String Name, int hp, int mp, int att, int AttRange) {
        this.Name = Name;
        this.hp = hp;
        this.mp = mp;
        this.att = att;
        this.AttRange = AttRange;
    }

    // 용사 정보 볼 때
    void info() {
        // Slime info = new Slime();
        System.out.println("---------------정보창---------------");
        System.out.println("이름: " + this.Name);
        System.out.println("체력: " + this.hp);
        System.out.println("마나: " + this.mp);
        System.out.println("공격력: " + this.att);
        System.out.println("공격범위: " + this.AttRange);
        System.out.println("------------------------------------");
    }

    // 용사 데미지 받을 때
    int dmg(int att) {
        this.hp -= att;
        System.out.println(this.Name + "은 " + att + "의 데미지를 받았습니다.");
        System.out.println(this.Name + "의 체력이 " + this.hp + " 남았습니다.");

        return this.hp;
    }

    // 용사 공격 할 때
    int att() {
        this.att = rand();
        System.out.println(this.Name + "이/가 데미지 " + this.att + " 의 공격을 했습니다.");

        return this.att;
    }

    // 랜덤 값 출력
    int rand() {
        Random rand = new Random();
        int mr = (int) (Math.random() * 10) + 1;
        return mr;
    }

    // 용사가 안죽고 살아있음
    void die() {
        if (hp <= 0) {
            hp = 0;
            System.out.println(this.Name + "이/가 죽었습니다.");
        } else {
            System.out.println(this.Name + "이/가 살아있습니다.");
        }
    }
}
