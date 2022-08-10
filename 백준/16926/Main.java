import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * BJ_16926_배열돌리기1
 * 
 * 시간제한 1초, 메모리 512MB
 * 
 * 크기가 N * M인 배열이 있을 때, 반시계방향으로 배열을 돌려보려고 한다.
 * 
 * N과 M중 하나는 무조건 짝수
 * 
 * @author djunnni
 *
 */
public class Main {
	public static int[][] matrix;
	public static int N, M, R, save;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 1부터 N, M까지 시작할 수 있도록 초기화 한다.
		matrix = new int[N + 1][M + 1]; 
		
		// 배열 내, 값 넣기
		for(int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int m = 1; m <= M; m++) {
				matrix[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 큰 사각형 -> 작은 사각형 -> 그 다음 사각형 똑같이 반복이 필요
		for(int r = 1; r <= R; r++) {
			int cnt = 0;
			while(1 + cnt <= M / 2 && 1 + cnt <= N / 2) {
				turn(1 + cnt, 1 + cnt, cnt);
				cnt++;
			}
		}
		StringBuilder sb = new StringBuilder();
		
		for(int y = 1; y <= N; y++) {
			for(int x = 1; x <= M; x++) {
				sb.append(matrix[y][x]);
				if(x != M) {
					sb.append(" ");
				} else {
					sb.append("\n");
				}
			}
		}
		System.out.println(sb);
	}
	/**
	 * x,y 좌표는 회전의 첫 시작점이다.
	 * cnt는 너비 또는 높이에서 cnt만큼을 빼고 돌리는 것.
	 */
	public static void turn(int x, int y, int cnt) {
		// 하
		for(int n_y = y; n_y < N - cnt; n_y++) {
			swap(x, y, x, n_y + 1);
			save = matrix[y][x];
		}
		// 우
		for(int n_x = x; n_x < M - cnt; n_x++) {
			swap(x, y, n_x + 1, N - cnt);
			save = matrix[y][x];
		}
		// 상
		for(int n_y = N - cnt; n_y > y; n_y--) {
			swap(x, y, M - cnt, n_y - 1);
			save = matrix[y][x];
		}
		// 좌
		for(int n_x = M - cnt; n_x > x; n_x--) {
			swap(x, y, n_x - 1, y);
			save = matrix[y][x];
		}
	}
	/**
	 * swap
	 * Y: 세로, X: 가로
	 */
	public static void swap(int x1, int y1, int x2, int y2) {
		int temp = matrix[y1][x1];
		matrix[y1][x1] = matrix[y2][x2];
		matrix[y2][x2] = temp;
	}
	public static void push(int x, int y, int value) {
		matrix[y][x] = value;
	}

}