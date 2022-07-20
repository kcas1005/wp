package Day41;

import java.util.*;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        boolean checkTrue = false;
        String[] gameProcess = {"성공", "성공", "실패", "성공", "뭐지"};
        ArrayList<String> savePoint = new ArrayList<>();
        int SuccessCountSave = 0;

        try {
            for (String success : gameProcess) {
                if (success.equals("성공")) {
                    System.out.println("1 -- 성공 하였습니다.");
                    savePoint.add(success);
                    SuccessCountSave++;
                } else if (success.equals("실패")) {
                    throw new Exception("2 -- 실패 하였습니다.");
                } else {
                    System.out.println("오류 입니다.");
                    throw new Exception("3 -- 알수 없는 오류 입니다.");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            checkTrue = true;
            System.out.println("checkTrue : " + checkTrue);
        } finally {
            System.out.println("다음 처리 된 프로세스를 출력합니다.");
            for (String saveContent : savePoint) {
                System.out.println(saveContent);
            }
        }
    }
}