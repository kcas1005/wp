package Day45.question01;

public class Portfolio {
    public static void main(String[] args) {

        try {
            Team tm = new Team();
//            tm.frontend.front_1("성진", "병진");
            tm.frontend.front_1("성진","병진");
            System.out.println("--------------------구분선-------------------");
            tm.backend.backend_1("영민", "승현");
            System.out.println("--------------------구분선-------------------");
            tm.database.database_1("명훈");
            System.out.println("---------------------구분선-------------------");
            tm.manager.manager_1("경섭");
            System.out.println("---------------------구분선-------------------");
            tm.team_check();



        } catch (StackOverflowError e) {
            System.out.println("스택오버플로 에러");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("왜 오류가 나지?");
            e.printStackTrace();

        } finally {

        }

    }
}
