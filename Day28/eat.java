import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class eat {
    public static void main(String[] arg) {

        // weekTest(Day());
        Day2();

    }

    public static void weekTest(int day) {

        Blueberry_Sandwich bs = new Blueberry_Sandwich();
        Lettuce_Sandwich ls = new Lettuce_Sandwich();
        Sweetpumpkin_Sandwich ss = new Sweetpumpkin_Sandwich();
        Eggsalad_Sandwich es = new Eggsalad_Sandwich();
        Avocado_Sandwich as = new Avocado_Sandwich();

        switch (day) {

            case 1:
                System.out.println("월요일에는 블루베리 샌드위치를 먹습니다.");
                bs.Blueberry_Sandwich("bread", "butter", "blueberry", "bacon");
                break;

            case 2:
                System.out.println("화요일에는 상추베이컨 샌드위치를 먹습니다.");
                ls.Lettuce_Sandwich("bread", "butter", "lettuce", "bacon");
                break;

            case 3:
                System.out.println("수요일에는 단호박 샌드위치를 먹습니다.");
                ss.Sweetpumpkin_Sandwich("bread", "butter", "sweet_pumpkin");
                break;

            case 4:
                System.out.println("목요일에는 에그샐러드 샌드위치를 먹습니다.");
                es.Eggsalad_Sandwich("bread", "butter", "salad", "egg");
                break;

            case 5:
                System.out.println("금요일에는 아보카도에그 샌드위치를 먹습니다.");
                as.Avocado_Sandwich("bread", "butter", "avocado", "egg");
                break;

            default:
                System.out.println("주말에는 쉽니다.");
                break;
        }
    }

    public static int Day() {
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        return day;
    }

    public static void Day2() {
        ArrayList<String>[] days = new ArrayList[5];

        for (int i = 0; i < 5; i++) {
            days[i] = new ArrayList<>();
        }
        days[0].add("1");
        days[0].add("월요일");
        days[0].add("블루베리 샌드위치");
        days[1].add("2");
        days[1].add("화요일");
        days[1].add("상추베이컨 샌드위치");
        days[2].add("3");
        days[2].add("수요일");
        days[2].add("단호박 샌드위치");
        days[3].add("4");
        days[3].add("목요일");
        days[3].add("에그샐러드 샌드위치");
        days[4].add("5");
        days[4].add("금요일");
        days[4].add("아보카도에그 샌드위치");

        int d1 = Integer.parseInt(days[0].get(0));
        String d11 = days[0].get(1);
        String d12 = days[0].get(2);
        int d2 = Integer.parseInt(days[1].get(0));
        String d21 = days[1].get(1);
        String d22 = days[1].get(2);
        int d3 = Integer.parseInt(days[2].get(0));
        String d31 = days[2].get(1);
        String d32 = days[2].get(2);
        int d4 = Integer.parseInt(days[3].get(0));
        String d41 = days[3].get(1);
        String d42 = days[3].get(2);
        int d5 = Integer.parseInt(days[4].get(0));
        String d51 = days[4].get(1);
        String d52 = days[4].get(2);

        Blueberry_Sandwich bs = new Blueberry_Sandwich();
        Lettuce_Sandwich ls = new Lettuce_Sandwich();
        Sweetpumpkin_Sandwich ss = new Sweetpumpkin_Sandwich();
        Eggsalad_Sandwich es = new Eggsalad_Sandwich();
        Avocado_Sandwich as = new Avocado_Sandwich();

        switch (d4) {

            case 1:
                System.out.println(d11 + "에는 " + d12 + " 를 먹습니다.");
                bs.Blueberry_Sandwich("bread", "butter", "blueberry", "bacon");
                break;

            case 2:
                System.out.println(d21 + "에는 " + d22 + " 를 먹습니다.");
                ls.Lettuce_Sandwich("bread", "butter", "lettuce", "bacon");
                break;

            case 3:
                System.out.println(d31 + "에는 " + d32 + " 를 먹습니다.");
                ss.Sweetpumpkin_Sandwich("bread", "butter", "sweet_pumpkin");
                break;

            case 4:
                System.out.println(d41 + "에는 " + d42 + " 를 먹습니다.");
                es.Eggsalad_Sandwich("bread", "butter", "salad", "egg");
                break;

            case 5:
                System.out.println(d51 + "에는 " + d52 + " 를 먹습니다.");
                as.Avocado_Sandwich("bread", "butter", "avocado", "egg");
                break;

            default:
                System.out.println("주말에는 쉽니다.");
                break;
        }
    }
}
