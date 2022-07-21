package Day45.question01;

public class Team extends Person {
    String[] team = new String[6];
    Person frontend = new Person() {
        String[] front = new String[2];
        int people;
        @Override
        void front_1(String input_front, String input_front2){
            front[0] = input_front;
            front[1] = input_front2;

            team[0] = front[0];
            team[1] = front[1];

            for (String f : front) {
                System.out.println(f);
            }
        }
    };

    Person backend = new Person() {
        String[] back = new String[2];


        @Override
        void backend_1(String input_back, String input_back2) {
            back[0] = input_back;
            back[1] = input_back2;

            team[2] = back[0];
            team[3] = back[1];

            for (String f : back) {
                System.out.println(f);
            }
        }
    };
    Person database = new Person() {
        String[] db = new String[1];

        @Override
        void database_1(String input_db) {
            db[0] = input_db;

            team[4] = db[0];

            for (String f : db) {
                System.out.println(f);
            }
        }
    };
    Person manager = new Person() {
        String[] mg = new String[1];
        @Override
        void manager_1(String input_mg) {
            mg[0] = input_mg;

            team[5] = mg[0];

            for (String f : mg) {
                System.out.println(f);
            }
        }
    };

    void team_check() {
        System.out.println("현재 팀 구성원은");
        for (String t : team) {
            System.out.println(t);
        }
    }

    /*void String team_find(){
        switch (true){
            case 1:

        }
        return team
    }*/
}
