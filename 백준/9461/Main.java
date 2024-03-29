import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * BJ_9461_파도반수열
 * 
 * 시간제한 1초
 * 메모리 128MB
 * 
 * 수열이 규칙에 따라 동작하고 있음.
 * ArrayList로 기본크기 100으로 정해두고 계산하기
 * => 최대 100인걸 알수 있어서.
 * 
 * @author djunnni
 *
 */
public class Main {
	public static int SIZE = 100;
	public static long[] defaultValue = {0,1,1,1,2,2,3,4,5,7,9};
	public static void main(String[] args) {
		
		List<Long> list = new ArrayList<>(SIZE + 1);
		for(long v : defaultValue) {
			list.add(v);
		}
		
		/**
		 * 수열 100까지 구해두기
		 */
		for(int i = 10; i <= SIZE; i++) {
			list.add(list.get(i) + list.get(i - 4));
		}
		
		// 해결 부
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test = 0; test < T; test++) {
			int spot = sc.nextInt();
			System.out.println(list.get(spot));
		}
		// 해결 부
	}
}
