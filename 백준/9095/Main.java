import java.util.Scanner;

/**
 * BJ_9095_1,2,3더하기
 * 
 * @author djunnni
 *
 */
public class Main {
    static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            // 1 ~ 11
            int n = sc.nextInt();

            dp = new int[n + 1]; // 1 ~ N까지

            dp[1] = 1; // 1을 만들 방법은 1하나
            if (n == 1) {
                System.out.println(dp[1]);
                continue;
            }

            dp[2] = 2; // dp[2] = 1 + 1, 2
            if (n == 2) {
                System.out.println(dp[2]);
                continue;
            }

            dp[3] = 4; // dp[3] = 1 + 1 + 1, 2 + 1, 1 + 2, 3;
            if (n == 3) {
                System.out.println(dp[3]);
                continue;
            }
            /**
             * dp[i] i번째
             * i-3은 dp[i-3]까지의 수에 앞뒤 3을 더하는 수
             * i-2는 dp[i-2]까지의 수에 앞뒤 2를 더하는 수
             * i-1은 dp[i-1]까지에서 앞뒤 1을 더하는 수
             */
            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
            }

            System.out.println(dp[n]);
        }
    }
}

// dp[4] = 7;
// 1 + 1 + 1 + 1
// 2 + 1 + 1
// 1 + 2 + 1
// 3 + 1
// 1 + 1 + 1 + 1 => 2 + 2
// 1 + 2 + 1 => X
// 1 + 1 + 2
// 1 + 3
//