import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_16935_배열돌리기3
 * 
 * 시간제한 2초, 메모리 512MB
 * 
 * 1. 상하반전
 * 2. 좌우반전
 * 3. 오른쪽 90도 회전
 * 4. 왼쪽 90도 회전
 * 5 ~ 6. N/2 x M/2의 부분배열로 쪼갠다.
 * 1, 2, 3, 4 로 시계방향으로 사분면을 나타낸다.
 * 
 * 5. 1 -> 2 -> 3 -> 4로 회전
 * 6. 1 -> 4 -> 3 -> 2로 회전
 * 
 * 제한 
 * 2 <= N, M <= 100
 * 1 <= R <= 1000
 * N, M 짝수
 * 내부에 들어갈 수 있는 값 최대 1억 (int 가능)
 * 
 * @author djunnni
 *
 */
public class Main {
	public static int[][] matrix;
	public static int[][][] splitMatrix;
	public static int N, M, R;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken()); // 명령어 개수
		
		// 1부터 N, M까지 시작할 수 있도록 초기화 한다.
		matrix = new int[N + 1][M + 1]; 
		
		// 배열 내, 값 넣기
		for(int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int m = 1; m <= M; m++) {
				matrix[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		String[] strs = br.readLine().split(" ");
		for(int r = 1; r <= R; r++) {
			run(strs[r - 1]);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int y = 1; y <= matrix.length - 1; y++) {
			for(int x = 1; x <= matrix[0].length -1; x++) {
				sb.append(matrix[y][x]);
				if(x == matrix[0].length - 1) {
					sb.append("\n");
				} else {
					sb.append(" ");
				}
			}
		}
		
		System.out.println(sb);
	}
	public static void run(String command) {
		switch(command) {
			case "1":
				action1();
				break;
			case "2":
				action2();
				break;
			case "3":
				action3();
				break;
			case "4":
				action4();
				break;
			case "5":
				splitArea();
				action5();
				break;
			case "6":
				splitArea();
				action6();
				break;
		}
	}
	// 상하 반전
	public static void action1() {
		for(int y = 1; y <= (matrix.length - 1) / 2; y++) {
			swapRow(y, matrix.length - 1 - y + 1);
		}
	}
	// 좌우 반전
	public static void action2() {
		for(int x = 1; x <= (matrix[0].length - 1) / 2; x++) {
			swapColumn(x, matrix[0].length - 1 - x + 1);
		}
	}
	// 오른쪽 90도
	public static void action3() {
		int N = matrix.length - 1;
		int M = matrix[0].length - 1;
		// 회전할 배열 생성
		int[][] temp_matrix = new int[M + 1][N + 1];
		
		for(int y = 1; y <= N; y++) {
			for(int x = 1; x <= M; x++) {
				temp_matrix[x][y] = matrix[N - y + 1][x];
			}
		}
		
		// 원본 덮어쓰기
		matrix = temp_matrix;
	}
	// 왼쪽 90도
	public static void action4() {
		int N = matrix.length - 1;
		int M = matrix[0].length - 1;
		
		// 회전할 배열 생성
		int[][] temp_matrix = new int[M + 1][N + 1];
		
		for(int y = 1; y <= N; y++) {
			for(int x = 1; x <= M; x++) {
				temp_matrix[x][y] = matrix[y][M - x + 1];
			}
		}
		// 원본 덮어쓰기
		matrix = temp_matrix;
	}
	// 4분면 쪼개기
	public static void splitArea() {
		int width = (matrix[0].length - 1) / 2;
		int height = (matrix.length - 1) / 2;
		
		// 0 => 1사분면, 1 => 2사분면, 3 => 3사분면, 2 => 4사분면
		splitMatrix = new int[4][height + 1][width + 1]; 
		// 너비 접근 1부터 width, 높이 접근 1부터 height
		int cnt = 0;
		int dx = 0; 
		int dy = 0;
		for(int y = 1; y <= (matrix.length - 1); y++) {
			for(int x = 1; x <= (matrix[0].length - 1); x++) {
				// 사분면 구분
				if(y <= height && x <= width) {
					cnt = 0;
					dx = 0;
					dy = 0;
				} else if(y <= height && x > width) {
					cnt = 1;
					dx = 1;
					dy = 0;
				} else if(y > height && x <= width) {
					cnt = 3;
					dy = 1;
					dx = 0;
				} else {
					cnt = 2;
					dx = 1;
					dy = 1;
				}		
				splitMatrix[cnt][y % (height + 1) + dy][x % (width + 1) + dx] = matrix[y][x];
			}
		}
	}
	//  5. 1 -> 2 -> 3 -> 4로 회전
	public static void action5() {
		int width = (matrix[0].length - 1) / 2;
		int height = (matrix.length - 1) / 2;
		
		int temp[][] = new int[height + 1][width + 1]; 
		
		// 0 => 1사분면, 1 => 2사분면, 3 => 3사분면, 2 => 4사분면
		temp = splitMatrix[3];
		splitMatrix[3] = splitMatrix[2];
		splitMatrix[2] = splitMatrix[1];
		splitMatrix[1] = splitMatrix[0];
		splitMatrix[0] = temp;
		
		join();
	}
	// 6. 1 -> 4 -> 3 -> 2로 회전
	public static void action6() {
		int width = (matrix[0].length - 1) / 2;
		int height = (matrix.length - 1) / 2;
		
		int temp[][] = new int[height + 1][width + 1]; 
		
		// 0 => 1사분면, 1 => 2사분면, 3 => 3사분면, 2 => 4사분면
		temp = splitMatrix[1];
		splitMatrix[1] = splitMatrix[2];
		splitMatrix[2] = splitMatrix[3];
		splitMatrix[3] = splitMatrix[0];
		splitMatrix[0] = temp;

		join();
	}
	// matrix에 4분면을 반영합니다.
	public static void join() {
		int width = (matrix[0].length - 1) / 2;
		int height = (matrix.length - 1) / 2;
		
		// 1사분면
		for(int y = 1; y <= height; y++) {
			for(int x = 1; x <= width; x++) {
				matrix[y][x] = splitMatrix[0][y][x];
			}
		}
		// 2사분면
		for(int y = 1; y <= height; y++) {
			for(int x = 1; x <= width; x++) {
				matrix[y][x + width] = splitMatrix[1][y][x];
			}
		}
		// 3사분면
		for(int y = 1; y <= height; y++) {
			for(int x = 1; x <= width; x++) {
				matrix[y + height][x] = splitMatrix[3][y][x];
			}
		}
		// 4사분면
		for(int y = 1; y <= height; y++) {
			for(int x = 1; x <= width; x++) {
				matrix[y + height][x + width] = splitMatrix[2][y][x];
			}
		}
	}
	public static void swap(int x, int y, int x2, int y2) {
		int temp = matrix[y][x];
		matrix[y][x] = matrix[y2][x2];
		matrix[y2][x2] = temp;
	}
	public static void swapRow(int y, int y2) {
		int[] temp = new int[(matrix[0].length - 1) + 1];
		temp = matrix[y];
		matrix[y] = matrix[y2];
		matrix[y2] = temp;
	}
	public static void swapColumn(int x, int x2) {
		for(int y = 1; y <= (matrix.length -1); y++) {
			swap(x, y, x2, y);
		}
	}
}
