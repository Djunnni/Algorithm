import java.util.Scanner;

/**
 * 거스름돈
 * DP
 * 2sec, 512MB
 *
 * 2원과 5원짜리로 거스름돈을 주기
 * 13 -> 5
 * 14 -> 4
 *
 */
public class Main {
    static int MAX_SIZE = 100_001;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 100_000
        if(N < 2) {
            System.out.println(-1);
            return;
        }

        int dp[] = new int[MAX_SIZE]; // i번에 해당하는 금액을 만들기 위해 필요한 최소 동전의 개수
        dp[2] = 1;
        dp[4] = 2;
        dp[5] = 1;

        for(int i = 6; i < MAX_SIZE; i++) {
            if(dp[i - 2] != 0 && dp[i - 5] != 0) {
                dp[i] = Math.min(dp[i - 2] + 1, dp[i - 5] + 1);
            } else if(dp[i - 2] != 0 && dp[i - 5] == 0) {
                dp[i] = dp[i - 2] + 1;
            } else if(dp[i - 5] != 0 && dp[i - 2] == 0) {
                dp[i] = dp[i - 5] + 1;
            }
        }

        System.out.println(dp[N] == 0 ? -1 : dp[N]);
    }
}
