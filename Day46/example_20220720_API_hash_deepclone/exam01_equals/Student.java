package Day46.example_20220720_API_hash_deepclone.exam01_equals;

public class Student {

    //필드
    //private : 접근제한자 : 같은 클래스 내에서만 접근 가능
    //student.name = "이준석"; 안됨!
    //public : 모두 다 접근 가능
    //default : 같은 패키지 내에서 접근 가능
    //protect : 같은 패키지 + 상속 내에서 접근 가능
    private String name;
    private int groupNum;

    //생성자 : 생성자는 클래스 이름과 동일
    //오버로딩(클래스의 스타일) : 생성자의 매개ㅐ변수를 통해 다양한 타입으로 생성
    //기본적으로 생성자(매개변수 없는) 1개는 있다(안보일 뿐)
//    public Student(){}

    public Student(String i_name, int i_groupNum) {

        //this : 제일 가까운 객체를 지칭 > Student라는 클래스
        //this.name = Student의 필드 name
        //this.name = Student의 필드 groupNum
        this.name = i_name;
        this.groupNum = i_groupNum;
    }

    //메서드
    //getter, setter 쓰는 이유 : 객체의 캡슐화, 데이터 입출력이라는 기능을 메서드를 잘 쓰기 위해서.
    //외부에서 필드값을 바로 조회 또는 수정을 막기 위해서! 객체는 딱 닫혀야 합니다!
    public int getGroupNum() {
        return groupNum;
    }

    public String getName() {
        return name;
    }

    public void setGroupNum(int i_groupNum) {
        this.groupNum = i_groupNum;
    }

    public void setName(String i_name) {
        this.name = i_name;
    }
    //Student 인스턴스 타입으로 아래 equals라는 메서드의 매개변수로 들어감(단, Object로 "타입" 형변환)
    @Override
    public boolean equals(Object obj) {
        //instanceof : 메모리 heap에 있는 인스턴스끼리 비교하는 것
        //상속받았다면 부모클래스도 동일하다가 true가 나옵니다
        if (obj instanceof Student) {
            Student student_1 = (Student) obj;
            if(groupNum == student_1.getGroupNum()){

            }
            /*if(id.equals(member.id)) {
                return true;*/
        }
        return false;
    }
}