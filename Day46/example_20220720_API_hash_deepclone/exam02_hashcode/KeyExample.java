package Day46.example_20220720_API_hash_deepclone.exam02_hashcode;

import java.util.HashMap;

public class KeyExample {
	//public : 접근제한자 : 모두다 접근 가능
	//static : 프로그램이 실행 되는 시점에서 1개만 만들어진 인스턴스
	//void : 이 메서드에는 리턴없다.
	//(String[] args) : 매개변수를 String 배열로 받겠다.
	public static void main(String[] args) {
		//Key 객체를 식별키로 사용해서 String 값을 저장하는 HashMap 객체 생성
		//HashMap이라는 타입으로 선언하되 안에 값은 Key라는 객체와 String문자열을 넣는다.
		//= Name HashMap<key, String> : Heap메모리에 인스턴스 만들기
		//HashMap<String, String> key위치는 String, Value 위치는 String
		//Key(String)으로 검색해서 Value(String)을 받는다 (HashMap의 구조)
		//HashMap 특징 : Key값은 중복 안됨
		//Key값을 "김준석", value "오전수업"
		//Key값을 "김준석", value "오후수업"
		//"김준석"을 검색하면 "오후수업"이 뜹니다
		HashMap<Key, String> hashMap = new HashMap<Key, String>();
		//Key라는 객체타입으로 testKey라는 변수명으로 타입 선언
		//=new Key(23); = heap메모리에서 Key라는 인스턴스를 생성(단, 인자값 23 넣음)
		//생성자(매개변수 23인) 실행
		Key testkey = new Key(23);
		Key testkey_4 = new Key(23);
		Key testkey_5 = new Key(23);

		System.out.println(testkey.hashCode());
		System.out.println(testkey_4.hashCode());
		//990368553
		System.out.println(testkey_5.hashCode());
		System.out.println("-----------------------");

		//json 포맷으로 인터넷 통신을 주로 합니다.(key, value)
		//식별키 "new Key(1)"로 "홍길동"을 저장함
		//hashMap에는 put() : hashmap에 데이터 넣기
		//String[] a; a[0] = "김준석"
		//a[0] = Key
		//"김준석" = "홍길동"
		//key값이 중복이 안되요~
		hashMap.put(new Key(1), "홍길동");
		hashMap.put(testkey, "김준석");
		hashMap.put(testkey, "오준석");

		
		//식별키 "new Key(1)" 로 "홍길동"을 읽어옴
		//hashmap.get() : 인자값(key)를 넣어서 key와 함께 넣음 value를 반환(출력)
		//hashMap.get() 메서드의 비교방식
		//new Key(1)이라고 get()에 매개변수를 넣으면
		//새로운 인스턴스가 만들어져요!
		//홍길동의 Key값과 다르다고 인식(인스턴스가 다르고, 주소가 다르므로 hashcode()도 다르기 때문
		//홍길동의 Key와 다르다고 인식 (hashcode()기반으로 구분 = HashMap의 자료저장 구조
		String value  = hashMap.get(new Key(1));
		System.out.println(value);
		//"오준석"이라는 문자열의 부모 해시코드 메서드
		System.out.println(hashMap.get(testkey).hashCode());
		
		Object obj = new Object();
		Object obj_1 = new Object();
		System.out.println("---------------------");
		System.out.println(obj);
		System.out.println(obj.hashCode());
		System.out.println(obj_1);
		System.out.println(obj_1.hashCode());
	}
}