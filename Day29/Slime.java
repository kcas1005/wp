import java.util.*;

public class Slime {
    // 슬라임 기본필드값
    String Name = "조금 강한 슬라임";
    int hp = 15;
    int mp = 0;
    int att = 1;
    int AttRange = 1;

    // 기본 슬라임 만들 때
    void slime() {
        this.Name = "평범한 슬라임";
        this.hp = 10;
        this.mp = 0;
        this.att = 1;
        this.AttRange = 1;
    }
    // 내가 원하는 슬라임 만들 때
    void slime(String Name, int hp, int mp, int att, int AttRange) {
        this.Name = Name;
        this.hp = hp;
        this.mp = mp;
        this.att = att;
        this.AttRange = AttRange;
    }
    // 슬라임 정보 볼 때
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
    // 슬라임 데미지입을 때
    int dmg(int att){
        this.hp -= att;
        System.out.println(this.Name + "은 " + att + "의 데미지를 받았습니다.");
        System.out.println(this.Name + "의 체력이 " + this.hp + " 남았습니다.");
        die();
        
        return this.hp;
    }
    // 슬라임 공격할 때
    int att(){
        // this.att = att;
        this.att = rand();
        System.out.println( this.Name + "이 데미지 " + this.att + " 의 공격을 했습니다." );

        return this.att;
    }
    // 랜덤 값 출력
    int rand(){
        Random rand = new Random();
        int mr =(int)(Math.random()*10)+1;
        return mr;  
    } 
    // 몬스터가 안죽고 살아있음
    void die(){
        if(hp <= 0){
            System.out.println(this.Name + "이/가 죽었습니다.");
        }else{
            System.out.println(this.Name + "이/가 살아있습니다.");
        }
    }
    // 몬스터 생성
    void create(){
        String[] slime_color = new String[4];

        slime_color[0] = "흰색 슬라임";
        slime_color[1] = "검은색 슬라임";
        slime_color[2] = "회색 슬라임";
        slime_color[3] = "파란색 슬라임";

        Random cr_ran = new Random();
        int cr_rand = (int)(Math.random()*slime_color.length-1);
        this.Name = slime_color[cr_rand];
        this.hp = (int)(Math.random()*50)+1;
        this.mp = (int)(Math.random()*50)+1;
        this.att = (int)(Math.random()*10)+1;
        this.AttRange = (int)(Math.random()*2)+1;

        
        
        
    }
}