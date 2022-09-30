
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * BJ_17070_파이프옮기기_1 
 * 
 * 1sec, 512mb
 * 
 * @author djunnni
 *
 */
public class Main2 {
	static char[][] map;
	static int N, dp[][][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 집의 크기 (3 <= N <= 16)
		N = Integer.parseInt(br.readLine());
		
		// 집 크기의 map 만들기
		map = new char[N][N];
		dp = new int[N][N][3];
		
		// 데이터 저장
		for(int i = 0; i < N; i++) {
			String[] data = br.readLine().split("");
			
			for(int j = 0, index = 0; j < N; j++, index += 2) {
				map[i][j] = data[index].charAt(0);
			}
		}
		
		// map 출력
//		for(char[] row : map) {
//			System.out.println(Arrays.toString(row));
//		}
		dp[0][1][0] = 1; // (1,0)으로 1가지 가는 방법이 있다.

		for(int i = 0; i < N; i++) {
			for(int j = 2; j < N; j++) {
				if(map[i][j] == '1') continue;
				if(!isOver(i, j - 1) && map[i][j-1] == '0') 
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
				if(!isOver(i - 1, j) && map[i-1][j] == '0') 
					dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
				if(!isOver(i - 1, j -1) && map[i - 1][j] == '0' && map[i][j - 1] == '0' && map[i - 1][j - 1] == '0') { 
					dp[i][j][1] = dp[i - 1][j-1][0] + dp[i - 1][j-1][1] + dp[i - 1][j -1 ][2];
				}
			}
		}
//		for(int[][] rowek : dp) {
//			for(int[] row : rowek) {				
//				int sum = row[0] + row[1] + row[2];
//				System.out.print(sum + " ");
//			}
//			System.out.println();
//		}
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}
	// 파이프의 상태를 알아봅니다.
	public static int getPosition(int y, int x, int endY, int endX) {
		if(x == endX && y == endY - 1) {
			// 90도
			return 2;
		} else if(endX - 1 == x && y == endY) {
			// 0도
			return 0;
		} else if(endX - 1 == x && endY - 1 == y) {
			// 45도
			return 1;
		}
		return 0;
	}
	public static boolean isOver(int y, int x) {
		if(y < 0 || x < 0 || y >= N || x >= N) {
			return true;
		}
		return false;
	}
}
