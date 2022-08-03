import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_11660_구간합구하기5
 * 
 * NxN의 수가 NxN에 채워져있다.
 * (x1,y1) -> (x2, y2)까지의 구간 합을 구하라
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
		
		int[][] matrix = new int[N + 1][N + 1]; // 구간 1 <= x <= N
		int[][] accumulateX = new int [N+1][N+1];
		
		// i가 가로, j가 세로
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				matrix[j][i] = Integer.parseInt(st.nextToken());
				accumulateX[j][i] = accumulateX[j][i - 1] + matrix[j][i];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int fromX = Integer.parseInt(st.nextToken());
			int fromY = Integer.parseInt(st.nextToken());
			int toX = Integer.parseInt(st.nextToken());
			int toY = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			// (fromX, fromY) -> (toX, toY)까지의 합을 구하라.
			for(int y = fromY; y <= toY; y++) {
				sum += (accumulateX[y][toX] - accumulateX[y][fromX - 1]);
			}
			
			
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
}
