
import java.io.*;
import java.util.*;

/**
 * @author djunnni
 */
public class Main {
	public static int ANSWER = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 값 초기화
		int[] numbers = new int[N];
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		// 풀이
		permutation(numbers, new int[N], 0);
		// 결과
		System.out.println(ANSWER);
	}
	public static void permutation(int[] numbers, int[] data, int visited) {
		int cnt = getOneCount(visited);
		if(cnt == numbers.length) {
			ANSWER = Math.max(ANSWER, getScore(data));
			return;
		}
		for(int i = 0; i < numbers.length; i++) {
			if((visited & (1 << i)) != 0) continue;
			visited |= (1 << i);
			data[cnt] = numbers[i];
			permutation(numbers, data, visited);
			visited &= ~(1 << i);
		}
	}
	public static int getOneCount(int v) {
		int cnt = 0;
		while(v != 0) {
			if((v & (1 << 0)) != 0) {
				cnt++;
			}
			v = v >> 1;
		}
		return cnt;
	}
	public static int getScore(int[] numbers) {
		int a = 0;
		// 2개씩 묶이는데 0번째가 큰값 1번째가 작은 값이 정해져야 한다.
		for(int i = 1; i < numbers.length; i++) {
			a += Math.abs(numbers[i - 1] - numbers[i]);
		}
		return a;
	}
}
