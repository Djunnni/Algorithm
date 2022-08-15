package chapter5_8;

import java.util.HashSet;
import java.util.Scanner;

public class Solution5_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W = N * 101;

        int[] a = new int[N];
        for(int i = 0; i < N; i++) a[i] = sc.nextInt();

        boolean[][] dp = new boolean[N + 1][W + 1];
        dp[0][0] = true;

        for(int i = 0; i < N; i++) {
            for (int w = 0; w <= W; w++) {
                if(dp[i][w]) {
                    dp[i + 1][w] = true;
                    dp[i + 1][w + a[i]] = true;
                }
            }
        }

        int count = 0;
        for(int j = 0; j <= W; j++) {
            if(dp[N][j])
                count++;
        }
        System.out.println(count);
    }
}
