package Day24;

import Day24.*;
import java.util.*;

/* 계획서
학생의 데이터를 받고, 객체를 출력해서 점수나, 코멘트를 본다

데이터를 받아오는 방식 : API(네트워크) / Excel 파일 읽어오기(파일 로딩) / CLI(terminal에서 데이터 받아오기,
Scanner)

조별 실행학습 = 취업, 평가지
김준석 성생님은 컴퓨터를 사고 싶다
프로그래밍으로 사고 싶은 컴퓨터의 성능지표를 출력 하는 시스템 개발하고 싶다
데이터를 받아오고, 그 데이터를 통해 컴퓨터의 성능을 출력하는 시스템개발
컴퓨터 이름(맥북pro 2021)
CPU성능
RAM
SSD(하드디스크)
단, 데이터가 없는 필드값에 대해 오버로딩으로 처리하세요.
Scanner로 데이터를 받아옵니다.
extra. 데이터가 부족할 경우, 여러 데이터를 동시에 받을 경우에 대해 고민해보세요
extra. 배열, if문, for문 복합적으로 쓰시면 어떨까요?
Scanner로 데이터를 받아옵니다. */


class Computer {

    public static void main(String[] arg){

        Scanner sc = new Scanner(System.in);

        System.out.println("컴퓨터 이름을 입력해주세요.");
        String inputComputerName = sc.next();
        System.out.println("computerName: " + inputComputerName);

        System.out.println("CPU 이름을 입력해주세요.");
        String inputCPUName = sc.next();
        System.out.println("CPUName: " + inputCPUName);

        System.out.println("RAM 이름을 입력해주세요.");
        String inputRAMName = sc.next();
        System.out.println("RAMName: " + inputRAMName);

        System.out.println("SSD 이름을 입력해주세요.");
        String inputSdName = sc.next();
        System.out.println("inputSSDname: " + inputSdName);

        System.out.print("computerName" + inputComputerName);
        System.out.print(" // ");
        System.out.print("CPUName: " + inputCPUName);
        System.out.print(" // ");
        System.out.print("RAMName: " + inputRAMName);
        System.out.print(" // ");
        System.out.print("SSDName: " + inputSdName);

        // ComputerChoice cp = new ComputerChoice();
        // System.out.println("cp.Name: " + cp.Name);
    }
}