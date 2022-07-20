package Day46.example_20220720_API_hash_deepclone.exam01_equals;

public class question1_memberExample {
    public static void main(String[] args) {
        question1_member qm1 = new question1_member("지성진", 1);
        question1_member qm2 = new question1_member("지성진", 1);
        question1_member qm3 = new question1_member("지성진", 2);
        question1_member qm4 = new question1_member("김준석", 1);

        if (qm1.equals(qm2)) {
            System.out.println("둘은 같습니다.");
        } else {
            System.out.println("같지 않습니다.");
        }
        System.out.println("-----------------구분선----------------- ");
        if (qm1.equals(qm3)) {
            System.out.println("둘은 같습니다.");
        } else {
            System.out.println("같지 않습니다.");
        }
        System.out.println("-----------------구분선----------------- ");
        if (qm3.equals(qm4)) {
            System.out.println("둘은 같습니다.");
        } else {
            System.out.println("같지 않습니다.");
        }
    }
}
