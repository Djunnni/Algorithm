import java.util.Scanner;

/**
 * BJ_2023_신기한소수
 * 
 * 제한 2초, 메모리 4MB
 * 
 * 수빈이가 세상에서 가장 좋아하는 것은 소수
 * 요즘 관심은 7331이다.
 * 
 * 7331은 소수인데 신기하게도 733도 소수 73도 소수 7도 소수다.
 * 즉, 왼쪽부터 1자리, 2자리,3자리,4자리 모두 소수다.
 * 
 * 수빈이는 이걸 신기한 소수라고 한다.
 * 
 * N자리의 숫자중에서 어떤수들이 신기한 소수인지 궁금해졌다. N이 주어졌을때,
 * 신기한 소수를 모두 찾아보자. -> 조합, 중복 가능
 * 
 * @author djunnni
 *
 */
public class Main {
	public static int SIZE;
	public static int N;
	public static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		arr = new int[N]; // 0 <= 범위 <= N - 1
		SIZE = 9;

		// 선택한 숫자가 없으므로 시작은 0
		find(0);
	}
	public static void find(int cnt) {
		// 지금까지 선택된 cnt를 가지고 값을 확인합니다.
		int v = getValue(arr, cnt);
		// cnt 0이 아닐경우 재대로 숫자가 들어있어 이때, 소수인지 체크
		if(cnt > 0 && !isDecimal(v)) {
			return;
		}
		// 길이가 N에 일치하면 안에 들어있는 value를 출력하고 종료
		if(cnt == N) {
			System.out.println(v);
			return;
		}
		
		// 1~9까지 선택한다며 루프돌기
		for(int i = 1; i <= SIZE; i++) {
			arr[cnt] = i;
			find(cnt + 1);
		}
	}
	// 숫자 생성
	public static int getValue(int[] arr, int cnt) {
		int value = 0;
		for(int i = 1; i <= cnt; i++) {
			value += Math.pow(10, cnt - i) * arr[i - 1];
		}
		return value;
	}
	// 소수 판정
	public static boolean isDecimal(int v) {
		if(v == 1) {
			return false;
		}
		boolean isPrime = true;
		for(int i = 2; i < v; i++) {
			if(v % i == 0) {
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}
}
