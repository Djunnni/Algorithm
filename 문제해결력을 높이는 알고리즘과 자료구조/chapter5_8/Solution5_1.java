package chapter5_8;

import java.util.Arrays;
import java.util.Scanner;

public class Solution5_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N+1][4];

        // j : 1 => 수영, 2 => 곤충 채집, 3 => 숙제
        for(int i = 1; i < N + 1; i++) {
            for(int j = 1; j < 4; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int[][] dp = new int[N+1][4];

        for(int i = 1; i < N + 1; i++) {
            dp[i][3] = arr[i][3] + Math.max(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = arr[i][1] + Math.max(dp[i - 1][2], dp[i - 1][3]);
            dp[i][2] = arr[i][2] + Math.max(dp[i - 1][1], dp[i - 1][3]);
        }

        System.out.println(Math.max(dp[N][1],Math.max(dp[N][2], dp[N][3])));
    }
}
