package acmicpc.BJ_11726;

import java.util.Scanner;

/**
 * acmicpc.BJ_11726_2xn 타일링
 * author djunnni
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] dp = new int[n + 1];

        /**
         * dp[i] => dp[i - 1]을 바탕으로 2x1이 추가된다.
         * 2x1이 2개면 1x2 2개로 전환이 가능하다.
         *
         */
        // dp[1]: |
        // dp[2]: ||, -
        // dp[3]: |||, |-, -|
        // dp[4]: ||||, ||-, |-|, -||, -- => (|||, |-, -|) + | , (||, -) + - => dp[3] +
        // dp[2]
        dp[1] = 1;
        if (n >= 2) {
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
            }
        }

        System.out.println(dp[n]);
    }
}
