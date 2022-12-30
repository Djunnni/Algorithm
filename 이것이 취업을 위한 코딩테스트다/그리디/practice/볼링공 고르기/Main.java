import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 볼링공 고르기
 * 1sec, 128MB
 * @author djunnni
 * 
 * 공이 제각각 몇개의 개수를 가지고 있는지
 * 자기자신을 제외하고 만들어 질 수 있는 경우의 수는 몇개인지
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 1000
		int M = Integer.parseInt(st.nextToken()); // 1 <= M <= 10
		
		int[] counts = new int[M + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			counts[Integer.parseInt(st.nextToken())]++;
		}
		
		int result = 0;
		for(int i = 1; i <= M; i++) {
			N -= counts[i];
			result += N * counts[i];
		}
		System.out.println(result);
	}
}
