import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BJ_11727_2xn타일링2
 * @author djunnni
 *
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int n = Integer.parseInt(br.readLine());
		
		// 2xn의 문제를 2xi의 문제로 바라보자.
		// 이때 2xi는 dp[i]로 표현할 수 있다고 보자.
		
		long dp[] = new long[n+1];
		
		dp[1] = 1; 
		if(n > 1) {
			dp[2] = 3;
		}
		
		for(int i = 1; i <= n; i++) {
			if(i > 2)
				dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
		}
		System.out.println(dp[n] % 10007);
//		i가 1이면 1			dp[1] = 1;
//		1x2			직사각형 1개
//		i가 2이면 3			dp[2] = 3;
//		2x2, 
//		1x2 + 1x2, 정사각형 1개, 직사각형 2개
//		2x1 + 2x1
//		i가 3이면 5			정사각형 1개, 직사각형 1개 => dp[2]
//		1x2 + 2x2,			
//		2x2 + 1x2 ,
//		1x2 + 1x2 + 1x2,
//		2x1 + 2x1 + 1x2		i[2] + [1]인 상황을 더한다.
//		1x2 + 2x1 + 2x1,
//		
//		i가 4면 11
//		2x2 + 2x2 => 1개
//		2x2 + 1x2 + 1x2 => 3개
//		1x2 + 2x2 + 1x2 
//		1x2 + 1x2 + 2x2
//		2x2 + 2x1 + 2x1 => 같은 케이스 3
//		1x2 + 1x2 + 1x2 + 1x2
//		1x2 + 1x2 + 2x1 + 2x1
//		2x1 + 2x1 + 1x2 + 1x2
//		1x2 + 2x1 + 2x1 + 1x2
//		
//		크기가 i인 직사각형을 채우려면
//		i - 1인경우 1x2 하나 차이이므로 dp[i-1]
//		i - 2인경우 긴사각형 2개 or 정사각형
//		dp[i-1] + 2 * dp[i - 2];
	}
}
