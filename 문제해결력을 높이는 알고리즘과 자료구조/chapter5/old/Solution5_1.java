package chapter5_1;

import java.util.Arrays;
import java.util.Scanner;

public class Solution5_1 {
    // 개구리 점프하기 문제와 유사.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
//        for(int i = 1; i < n; i++) {
//            // 끌기 전이
//            int i1 = arr[i] - arr[i - 1];
//            if(i == 1) dp[i] = Math.min(dp[i], i1);
//            else dp[i] = Math.min(
//                    dp[i - 1] + Math.abs(i1),
//                    dp[i - 2] + Math.abs(arr[i] - arr[i-2])
//            );
//        }

        for(int i = 0; i < n; i++) {
            // 밀기 전이
            if(i + 1 < n) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i] + Math.abs(arr[i] - arr[i + 1]));
            }
            if(i + 2 < n) {
                dp[i + 2] = Math.min(dp[i + 2], dp[i] + Math.abs(arr[i] - arr[i + 2]));
            }
        }

        System.out.println(dp[n-1]);

    }
}
