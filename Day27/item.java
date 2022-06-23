package Day27;

import java.util.*;

public class item {
    static int Data;
    static String[] Item_List = { "고양이 검", "개 검", "코끼리 검", "고릴라 검" };
    static String[] Item_Monster = new String[10];
    
    public static void main(String[] arg) {

        int random = (int) (Math.random() * 101) + 0;
        if (random > 80) {
            System.out.println("아이템 획득");
        } else {
            System.out.println("아이템 획득 실패");
            MonsterItem();
        }
        
    }
    
    public static int Itemlist(String[] Item_List) {
        
        int Item_random = (int) (Math.random() * Item_List.length) + 0;
        int Data = Item_random;
        System.out.println("Data: " + Data);
        return Data;
    }

    public static void MonsterItem(){
        int MIData = Itemlist(Item_List);
        for(int i=0; i<Item_Monster.length; i++){
        if(Item_Monster[i]==null){
            // Item_Monster[i] = Item_List[MIData];
            Item_Monster[i] = add(Item_List[MIData]);
            
            System.out.println(i + "번째 창에 "+ Item_List[MIData] +" 아이템을 집어넣습니다.");
            System.out.println("아이템 창 확인 : " + Arrays.toString(Item_Monster));
            break;
        }else{
            System.out.println("인벤토리 창 확인 하세요.");
            System.out.println("아이템 창 확인 : " + Arrays.toString(Item_Monster));
            break;
        }

    }

    }

}
