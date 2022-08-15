package algorithm_ds.chapter5;

import java.util.Arrays;
import java.util.Scanner;

public class Solution5_1_recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int arr [] = new int [N];
        int dp[] = new int [N];

        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.fill(dp, Integer.MAX_VALUE);
        int answer = 0;
        dp[0] = 0;

        rec(arr, dp, N - 1);

        answer = dp[N - 1];
        System.out.println(answer);
    }
    public static int rec(int[] arr, int[] dp, int i) {
        if(dp[i] < Integer.MAX_VALUE) {
            return dp[i];
        }
        if(i == 0) {
            return 0;
        }

        int res = Integer.MAX_VALUE;

        res = Math.min(res, rec(arr, dp, i - 1) + Math.abs(arr[i] - arr[i - 1]));

        if(i > 1) {
            res = Math.min(res, rec(arr, dp, i - 2) + Math.abs(arr[i] - arr[i-2]));
        }
        return dp[i] = res;
    }

}
