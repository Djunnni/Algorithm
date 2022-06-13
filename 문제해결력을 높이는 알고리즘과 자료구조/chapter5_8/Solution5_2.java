package chapter5_8;

import java.util.Scanner;

public class Solution5_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W = sc.nextInt();

        int[] a = new int[N + 1];
        for(int i = 1; i <= N; i++) a[i] = sc.nextInt();

        boolean[][] dp = new boolean[N + 1][W + 1];

        for(int i = 1; i < N; i++) {
            // 자기 자신만 더했을 때,
            if (W - a[i] >= 0) {
                dp[i][a[i]] = true;
                dp[i][0] = true;
            }
            for (int w = 0; w <= W; w++) {
                if(dp[i][w]) {
                    dp[i + 1][w] = dp[i][w];
                    if(w + a[i + 1] <= W) {
                        dp[i + 1][w + a[i + 1]] = dp[i][w];
                    }
                }
            }
        }
        
        boolean exist = false;
        for(int i = 0; i <= N; i++) {
            if(dp[i][W]) {
                exist = true;
            }
        }
        System.out.println(exist ? "Yes" : "No");
    }
}
