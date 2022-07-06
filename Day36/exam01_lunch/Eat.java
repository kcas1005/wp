package Day36.exam01_lunch;

public class Eat {
    String who;
    String something;

    //사람을 고르는 것
    public String Select_People(People input_people) {
        if (input_people instanceof Kim) {
            System.out.println("Kim(을/를) 골랐습니다.");
            who = "Kim";
            } else if (input_people instanceof Park) {
                System.out.println("Park(을/를) 골랐습니다.");
            who = "Park";
            } else if (input_people instanceof Lee) {
                System.out.println("Lee(을/를) 골랐습니다.");
            who = "Park";
        } else {
            System.out.println("유효하지 않는 값 입니다.");
        } return who;
    }


        //젤리를 고르는 것
        public String Select_Jelly(Jelly input_jelly) {
            if (input_jelly instanceof Mango) {
                System.out.println("Mango(을/를) 골랐습니다.");
                something = "Mango";
            }else if (input_jelly instanceof Strawberry) {
                System.out.println("Strawberry(을/를) 골랐습니다.");
                something = "Strawberry";
            }else if(input_jelly instanceof Grape) {
                System.out.println("Grape(을/를) 골랐습니다.");
                something = "Grape";
            }else{
                    System.out.println("유효하지 않는 값 입니다.");
            } return something;
                }

        //먹는 것
        public void Eating(String who, String something){
            System.out.println(who + "(이/가) " + something + "(을/를) 먹습니다.");
        }



        // 사람 A,B,C 등 여러사람이 있습니다.
        // 젤리 중에 망고, 포도, 딸기가 있습니다.
        // 골라서, 먹습니다.

}


