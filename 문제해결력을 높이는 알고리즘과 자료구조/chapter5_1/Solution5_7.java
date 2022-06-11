package chapter5_1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Solution5_7 {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long W = sc.nextLong();
        int[] weight = new int[N];
        int[] value = new int[N];

        for(int i = 0; i < N; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        long[][] dp = new long[N + 1][(int) (W + 1)];

        for(int i = 0; i < N; i++) {
            for(int w = 0; w <= W; w++) {
                if(w - weight[i] >= 0) {
                    dp[i + 1][w] = Math.max(dp[i+1][w], dp[i][w - weight[i]] + value[i]);
                }
                dp[i+1][w] = Math.max(dp[i+1][w], dp[i][w]);
            }
        }
        System.out.println(dp[N][(int) W]);
    }
}
