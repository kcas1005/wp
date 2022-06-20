package Day24;

// 계획서
// 학생의 데이터를 받고, 객체를 출력해서 점수나, 코멘트를 본다

// 데이터를 받아오는 방식 : API(네트워크) / Excel 파일 읽어오기(파일 로딩) / CLI(terminal에서 데이터 받아오기,
// Scanner)

//조별 실행학습 = 취업, 평가지
//김준석 성생님은 컴퓨터를 사고 싶다
//프로그래밍으로 사고 싶은 컴퓨터의 성능지표를 출력 하는 시스템 개발하고 싶다
//데이터를 받아오고, 그 데이터를 통해 컴퓨터의 성능을 출력하는 시스템개발
//컴퓨터 이름(맥북pro 2021)
//CPU성능
//RAM
//SSD(하드디스크)
//단, 데이터가 없는 필드값에 대해 오버로딩으로 처리하세요.
//Scanner로 데이터를 받아옵니다.
//extra. 데이터가 부족할 경우, 여러 데이터를 동시에 받을 경우에 대해 고민해보세요
//extra. 배열, if문, for문 복합적으로 쓰시면 어떨까요?
//Scanner로 데이터를 받아옵니다.

// Scanner 인스턴스 생성
public class ComputerChoice {
    String Name = "지성진";
    String computerName = "그램";
    String CPUName = "인텔";
    String RAMName = "64gb";
    String SSDName = "1tb";

    // 인자값 넣을 때
    public ComputerChoice() {
    }

    public ComputerChoice(String input_computerName) {
        this.computerName = input_computerName;
    }

    public ComputerChoice(String input_computerName, String input_CPUName) {
        this.computerName = input_computerName;
        this.CPUName = input_CPUName;
    }

    public ComputerChoice(String input_computerName, String input_CPUName, String input_RAMName) {
        this.computerName = input_computerName;
        this.CPUName = input_CPUName;
        this.RAMName = input_RAMName;
    }

    public ComputerChoice(String input_computerName, String input_CPUName, String input_RAMName, String input_SSDName) {
        this.computerName = input_computerName;
        this.CPUName = input_CPUName;
        this.RAMName = input_RAMName;
        this.SSDName = input_SSDName;
    }

    // 넣은 인자값이랑 실제로 있는지 확인하는 것
    public BooleanCheck(String inputComputerName){
    if(inputComputerName == this.computerName){
        System.out.println("값이 일치합니다.");
    }
    else{
        System.out.println("값이 다릅니다.");
    }
}
    // 없을 때 추가하는 것
}