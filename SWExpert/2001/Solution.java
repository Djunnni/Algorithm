import java.util.Arrays;
import java.util.Scanner;

/**
 * D2_2001_파리퇴치
 * 배열 내에서 해당하는 크기만큼을 잡아 가장 큰 수를 리턴합니다.
 * author djunnni
 */
public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {

			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int matrix[][] = new int[N + 1][N + 1];
			int accumulate[][] = new int[N + 1][N + 1];
			
			// i 는 열, j는 행
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					matrix[i][j] = sc.nextInt();
					accumulate[i][j] = matrix[i][j] + accumulate[i][j - 1];
				}
			}
			
			int MAX = Integer.MIN_VALUE;
			for(int i = 1; i <= N - M + 1; i++) {
				for(int j = 1; j <= N - M + 1; j++) {
					int sum = 0;
					for(int k = 0; k < M; k++) {
						sum += accumulate[i + k][j + M - 1] - accumulate[i + k][j - 1];
					}
					if(sum > MAX) {
						MAX = sum;
					}
				}
			}
			
			System.out.println("#" + test_case + " " + MAX);
			
		}
	}
}
