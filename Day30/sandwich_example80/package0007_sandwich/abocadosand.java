package sandwich_example80.package0007_sandwich;

import sandwich_example80.package0007_sandwich.*;

public class abocadosand extends superClass_sandwich {
    
    //이렇게 만들다 보니 다른 샌드위치와 차이가 없네요..
    public abocadosand(String name, String bread, String butter, String material_1, String material_2 ){
        super(name, bread, butter);
    }

    @Override
    public void getInfo(String day){
        System.out.println("이름은은 "+name+"입니다, 오늘은 "+day+"입니다");
        System.out.println("메인재료1은 "+bread+"입니다");
        System.out.println("메인재료2는 "+butter+"입니다");
    }
}