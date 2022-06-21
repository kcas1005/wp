package slimeLand;

public class Warrior{
    public String name;
    public int hp;
    public int mp;

    public Warrior(String input_name, int input_hp, int input_mp){
        this.name = input_name;
        this.hp = input_hp;
        this.hp = input_hp;
    }
    public int att(){
        return 10;
    }

    public int att_A(){
        this.mp = this.mp - 10;
        return 30;
    }
    public int att_B(){
        this.mp = this.mp - 20;
        return 50;
    }
    public void dmg(int input_dmg){
        this.hp = this.hp - input_dmg;

    }

    public void info(){
        System.out.println("__________"+name+"__________");
        System.out.println("HP = " + this.name);
        System.out.println("HP = " + this.hp);
        System.out.println("HP = " + this.mp);
    }
    public int hit() {
        double random = Math.random();
        int intValue = (int) (random * 11) + 0;
        if (intValue > 2) {
            System.out.println(intValue +"공격에 성공했습니다.");
         } else {
             System.out.println("공격에 실패했습니다.");
         }

        return intValue;
    }
    public void die(){
        if(this.hp<=0){
            System.out.println(this.name + "이/가 죽었습니다.");
        }
        else{

        }
    }

}
