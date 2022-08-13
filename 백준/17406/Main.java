import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, K, answer;
	public static int[][] matrix, temp;
	public static int[][] commands;
	public static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 0부터 N - 1, M - 1까지 시작할 수 있도록 초기화 한다.
		matrix = new int[N][M]; 
		
		// 배열 내, 값 넣기
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int m = 0; m < M; m++) {
				matrix[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		commands = new int[K][3];
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			commands[k][0] = r;
			commands[k][1] = c;
			commands[k][2] = s;
		}
		
		answer = Integer.MAX_VALUE;
		isSelected = new boolean[K];
		loop(0);
		
		System.out.println(answer);
		
	}
	public static void loop(int cnt) {
		if(cnt == 0) {
			temp = copy(matrix);
		}
		if(cnt == K) {
			int min = getMinimun(temp);
			answer = Math.min(answer, min);
//			System.out.println("min: " +min);
//			System.out.println("answer: " +answer);
			
			return;
		}
		
		for(int k = 0; k < K; k++) {
			if(isSelected[k]) {
				continue;
			}
			isSelected[k] = true;
			int[][] tempCopy = copy(temp);
			// 회전
			rotate(temp, commands[k][0], commands[k][1], commands[k][2]);
			// 루프 더 돌기
//			System.out.println(Arrays.toString(isSelected));
//			System.out.println("=============================");
			loop(cnt + 1);
			isSelected[k] = false;
			temp = tempCopy;
		}
	}
	public static void rotate(int[][] temp, int r, int c, int s) {
		// -1을 해주는 이유는 0이 배열로 1이기 때문이다.
		int fromX = c - s - 1;
		int fromY = r - s - 1;
		int toX = c + s - 1;
		int toY = r + s - 1;
		
		int cnt = 0;
		while(cnt < (toX - fromX) / 2) {
			rotate(temp, fromX, fromY, toX, toY, cnt);
			cnt++;
		}
//		for(int[] i : temp) {
//			System.out.println(Arrays.toString(i));
//		}
//		 System.out.println("=========================");
	}
	public static void rotate(int[][] matrix, int fromX, int fromY, int toX, int toY, int cnt) {
	//	System.out.println(fromX + " " + fromY + " " + toX + " " + toY + " " + cnt);
		// 우
		for(int nX = fromX + cnt; nX < toX - cnt; nX++) {
			swap(matrix, fromX + cnt, fromY + cnt, nX + 1, fromY + cnt);
		}
		// 하
		for(int nY = fromY + cnt; nY < toY - cnt; nY++) {
			swap(matrix, fromX + cnt, fromY + cnt, toX - cnt, nY + 1);
		}
		// 좌
		for(int nX = toX - cnt; nX > fromX + cnt; nX--) {
			swap(matrix, fromX + cnt, fromY + cnt, nX - 1, toY - cnt);
		}
		// 상
		for(int nY = toY - cnt; nY > fromY + cnt; nY--) {
			swap(matrix, fromX + cnt, fromY + cnt, fromX + cnt, nY - 1);
		}
	}
	public static void swap(int[][] map, int x, int y, int x2, int y2) {
		int temp = map[y][x];
		map[y][x] = map[y2][x2];
		map[y2][x2] = temp;
	}
	public static int[][] copy(int[][] matrix) {
		int[][] temp = new int[matrix.length][matrix[0].length];
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				temp[i][j] = matrix[i][j];
			}
		}
		return temp;
	}
	public static int getMinimun(int[][] matrix) {
		int min = Integer.MAX_VALUE;
		
		for(int[] arr : matrix) {
			int sum = Arrays.stream(arr).sum();
			if(min > sum) {
				min = sum;
			}
		}
		return min;
	}
}
