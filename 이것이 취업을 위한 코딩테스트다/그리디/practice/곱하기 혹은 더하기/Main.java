import java.util.Scanner;

/**
 * 곱하기 혹은 더하기
 * 1sec, 128MB
 * 
 * @author djunnni
 *
 * [목표]
 * 각 자리 숫자는 0 - 9 인 문자열로 왼쪽에서 오른쪽으로 숫자를 확인해 x, + 를 넣어
 * 만들어질 수 있는 가장 큰 수를 찾아라
 * 
 * [풀이]
 * spot에 0 또는 1일 경우 +, 그외는 x로 계산해보자
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String number = sc.nextLine(); // 숫자 받기
		
		int answer = 0;
		for(char num : number.toCharArray()) {
			int current = num - '0';
			if(current == 0 || current == 1 || answer == 0 || answer == 1) { 
                // 현재 값이 0이나 1, answer가 0이나 1이면 +를 한다.
				answer += current;
			} else { 
                //그 외는 x를 한다.
				answer *= current;
			}
		}
		
		System.out.println(answer);
	}
}
