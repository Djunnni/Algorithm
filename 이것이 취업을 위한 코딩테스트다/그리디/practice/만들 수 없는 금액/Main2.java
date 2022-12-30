import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 만들 수 없는 금액 [백준 2437]
 * 1sec, 128MB
 * @author djunnni
 * 
 * [접근방법]
 * 더하기다 보니 오름차순으로 정렬 한 뒤
 * 1 + 1 + 2 + 3 => 1 + 1 + (1 + 1) + (1 + 1 + 1)로 접근
 * 그러면 숫자를 더해서 다음에 나올 경우와 비교해 작다면 그게 정답
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 주어진 동전의 개수
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] numbers = new int[N]; 
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers); // 오름차순으로 정렬 시키기
		
		int result = 1;
		int one = 0;
		for(int number : numbers) {
			if(result < number) {
				break;
			}
			one += number;
			result = one + 1;
			
		}
		
		System.out.println(result);
	}

}
/*
5
3 2 1 1 9
=> 8

3
3 5 7
=> 1
*/