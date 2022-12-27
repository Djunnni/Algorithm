package dongbin.greedy.practice;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 문자열 뒤집기 [실패]
 * 2sec, 128MB
 * @author djunnni
 * 
 * [실패의 원인은?]
 * 구간별 카운팅을 잘 하지 못했음.
 * 
 * [대안은?]
 * 기초 간단 배열 내 구현 연산문제 많이 풀어보기
 *
 * [문제 접근은?]
 * 0, 1 두개의 숫자로 이루어져 있어 각각이 뒤집힐 때, 가능한 숫자를 세어보고 그중 작은 값을 고르기로 했다.
 * 
 */
public class Question3 {
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		String str = sc.nextLine();
//		StringTokenizer st = new StringTokenizer(str, "0");
//		StringTokenizer st2 = new StringTokenizer(str, "1");
//		
//		System.out.println(Math.min(st.countTokens(), st2.countTokens()));
//	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		int zero = 0, one = 0;
		
		if(str.charAt(0) == '0') zero++;
		else one++;
		
		for(int i = 1; i < str.length(); i++) {
			if(str.charAt(i) != str.charAt(i - 1)) {
				if(str.charAt(i) == '0') zero++;
				else one++;
			}
		}
		System.out.println(Math.min(zero, one));
	}
}
