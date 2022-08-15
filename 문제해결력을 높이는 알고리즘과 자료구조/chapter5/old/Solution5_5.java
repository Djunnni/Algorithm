package chapter5_1;

import java.util.Arrays;
import java.util.Scanner;

public class Solution5_5 {
    public static int rec(int i, int[] arr, int[] dp) {
        if(dp[i] < Integer.MAX_VALUE) {
            return dp[i];
        }
        if(i == 0) return 0;

        int res = Integer.MAX_VALUE;

        res = Math.min(res, rec(i - 1, arr, dp) + Math.abs(arr[i] - arr[i - 1]));
        if( i > 1) {
            res = Math.min(res, rec(i - 2, arr, dp) + Math.abs(arr[i] - arr[i - 2]));
        }
        return dp[i] = res;
    }
    public static void main(String[] args) {
        // 재귀
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        System.out.println(rec(n - 1, arr, dp));

    }
}
