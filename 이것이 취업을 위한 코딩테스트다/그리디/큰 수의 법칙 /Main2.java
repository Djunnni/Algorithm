import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 그리디 2번 큰 수의 법칙
 * 1sec, 128MB
 * @author djunnni
 * 
 * O(N)
 *
 */
public class Main2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 2 <= N <= 1000, 자연수의 개수
		int M = Integer.parseInt(st.nextToken()); // 1 <= M <= 10_000, M번 더할 수
		int K = Integer.parseInt(st.nextToken()); // 1 <= K <= 10_000, K번 초과해서 더할 수 없는 수
		
		st = new StringTokenizer(br.readLine());
		
		int numbers[] = new int[N]; // N개의 정수가 들어갈 변수
		
		int i = 0; // i번째부터 토큰이 존재할 때까지 numbers 배열에 변수 삽입
		
		int max = Integer.MIN_VALUE;
		int nextMax = Integer.MIN_VALUE;
		
		while(st.hasMoreTokens()) {
			numbers[i++] = Integer.parseInt(st.nextToken());
			if(i == 1) {
				max = numbers[i - 1];
				nextMax = 0;
			} else {
				if(max <= numbers[i - 1]) {
					nextMax = max;
					max = numbers[i - 1];
				}
			}
		}
		
		int result = (M / (K + 1)) * (K * max + nextMax) + max * (M % (K + 1));
		System.out.println(result);		

		// M에 대해서도 재사용 고려해보기 [TODO]
		// int x = ((M / (K + 1)) * K + (M % (K + 1)));
		// int y = M - x;
		// System.out.println(x * max + y * nextMax);		
	}

}
/*
5 8 3
2 4 5 4 6
=> 46

5 7 2
3 4 3 4 3
=> 28

2 2 1
5 7
=> 12

2 3 2
10 4
=> 20
*/