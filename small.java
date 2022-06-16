import java.util.ArrayList;

class small {
    static void main(String[] args) {
		int[][] score = { { 79, 80, 99 }, // A학생
						  { 95, 85, 89 }, // B학생
						  { 90, 65, 56 }, // C학생
						  { 69, 78, 77 } }; // D학생
					// 과목 : a   b   c
		int[] student = new int[4]; // 학생 총점 배열 생성
		int[] subject = new int[3]; // 과목 총점 배열 생성
		String[] stuName = {"A학생", "B학생", "C학생", "D학생"}; // 학생명 배열 저장
		String[] subName = {"영어", "수학", "과학"}; // 과목명 배열 저장

		for (int i = 0; i < student.length; i++) {
			for (int j = 0; j < subject.length; j++) {
				student[i] += score[i][j]; // 학생별 총점 계산
			}
			System.out.println(stuName[i] + " 총점 : " + student[i]);
		}
		System.out.println("-----------------");
		for (int j = 0; j < subject.length; j++) {
			for (int i = 0; i < student.length; i++) {
				subject[j] += score[i][j]; // 과목별 총점 계산
			}
			System.out.println(subName[j]+" 총점 : " + subject[j]);
		}
	}
}
https://m.blog.naver.com/heartflow89/220950845259