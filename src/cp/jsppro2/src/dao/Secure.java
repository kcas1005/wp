package dao;

import java.util.Random;



public class Secure {
	private static int length = 16;
	private static int[] plus = new int[length];
	public static Random ranGen = new Random();
	
	static {
		//암호표 생성
		for(int i=0; i<length; i++)
			plus[i] = i;
	}
	
	public static int lengthStr(String str) {
		int length = str.length();
		return (length+plus[0]);
	}
	
	public static String changeStr(String str) {
		//ascii 코드는 최소 10000000까지 있다.
		//pw가 16글자로 더 길다
		
		//문자를 배열에 넣음
		char[] cStr = new char[length];
		for(int i=0;i<length;i++) {
			if(i>=str.length())
				cStr[i] = 'a';
			else
				cStr[i] = str.charAt(i);
		}
		
		//문자를 정해진 숫자만큼 늘림(아스키)
		for(int i=0; i<length; i++) {
			cStr[i] = (char)(((int)cStr[i])+plus[i]);
		}
		
		String nStr = new String(cStr);
		return nStr;
	}
}
