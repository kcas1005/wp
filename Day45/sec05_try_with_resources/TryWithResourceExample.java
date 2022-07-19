package Day45.sec05_try_with_resources;

public class TryWithResourceExample {
	public static void main(String[] args) {
		//트라이가 끝나면 자동으로 입력문을 닫아줘서 자원 관리가 가능
		try (FileInputStream fis = new FileInputStream("file.txt")) {
			fis.read();
			throw new Exception();
		} catch(Exception e) {
			System.out.println("예외처리 코드가 실행되었습니다");
			e.printStackTrace();
		}
	}
}
