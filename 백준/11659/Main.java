import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_11659_구간합구하기4
 * 수 N개가 주어졌을 때, i번째 부터 j번째까지의 합을 구하라
 * 
 * 첫쨰 줄에 수의 개수 N, 합을 구해야하는 횟수 M
 * 두번째 줄에는 N개의 수
 * 
 * 수는 100보다 작거나 같은 수
 * 
 * 3번째 중에는 M개의 줄에는 합을 구해야 하는 구간 i j가 주어진다.
 * 
 * @author djunnni
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] accumulates = new int [N + 1];
		int[] arr = new int[N + 1]; // 구간은 1 <= x <= N까지로 한다.
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			accumulates[i] = accumulates[i - 1] + arr[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			sb.append(accumulates[to] - accumulates[from - 1]).append("\n");
		}
		
		System.out.println(sb);
	}

}
