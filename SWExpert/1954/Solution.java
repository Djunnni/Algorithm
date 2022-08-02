import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * D2_1954_달팽이 숫자
 * 달팽이는 1 부터 N ^2까지 시계방향으로 이루어져있다.
 * N을 입력받아 달팽이의 크기를 출력해라.
 * @author djunnni
 *
 */
public class Solution {
	// 우 -> 아래 -> 좌 -> 위
	public static int[] dx = { 1, 0, -1, 0}; // 가로
	public static int[] dy = { 0, 1, 0, -1}; // 세로
	public static int current;
	public static int visit;
	public static int N;
	public static int[][] matrix;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/com/ssafy/lab/SW_1954/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int test = 1; test <= T; test++) {
			N = sc.nextInt();
			
			matrix = new int[N][N];
			visit = 1;
			current = 0;
			
			trace(0, 0);
			
			System.out.println("#" + test);
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					System.out.print(matrix[y][x] + " ");
				}
				System.out.println();
			}
		}
	}
	public static void trace(int spotX, int spotY) {
		// 벽을 만나거나 이미 숫자가 들어있다면,
		if(isWall(spotX,spotY) || matrix[spotY][spotX] != 0) {
			return;
		}
		// 방문한 곳에 visit 남기기
		matrix[spotY][spotX] = visit++;
		
		// nextX, nextY 구하기
		int nextX = spotX + dx[current % dx.length];
		int nextY = spotY + dy[current % dy.length];
		
		// c는 4방으로 돌았는지 체크하는 수
		int c = 1;
		while(true) {
			// 만약 벽이 아니거나 해당 부분에 값이 비어있다면
			if(!isWall(nextX, nextY) && matrix[nextY][nextX] == 0) {
				break;
			} else {
				// c를 1증가 nextX, nextY를 변경
				c++;
				// c가 4번이나 찼으면 빠져나오기
				if(c == 4) { break;}
				current++;
				nextX = spotX + dx[current % dx.length];
				nextY = spotY + dy[current % dy.length];
			}
		}
		// 나온 걸로 다음구간 돌기
		trace(nextX,nextY);
	}
	// 벽인지 체크
	public static boolean isWall(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= N) {
			return true;
		}
		return false;
	}
}
