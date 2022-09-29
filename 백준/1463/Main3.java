import java.util.Arrays;
import java.util.Scanner;

/**
 * acmicpc.BJ_1463_1로_만들기
 * author djunnni
 */
public class Main3 {
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        // DFS
        answer = Integer.MAX_VALUE;
        DFS(N, 0);
        System.out.println(answer);

        // DP
        System.out.println(DP(N));

    }

    public static void DFS(int n, int count) {
        if (n == 1) {
            answer = Math.min(answer, count);
            return;
        }

        if (n % 3 == 0)
            DFS(n / 3, count + 1);
        if (n % 2 == 0)
            DFS(n / 2, count + 1);
        DFS(n - 1, count + 1);
    }

    public static int DP(int N) {
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = dp[1] = 0;

        for (int i = 1; i <= N; i++) {
            if (i * 3 <= N)
                dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
            if (i * 2 <= N)
                dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            if (i + 1 <= N)
                dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
        }
        return dp[N];
    }
}
