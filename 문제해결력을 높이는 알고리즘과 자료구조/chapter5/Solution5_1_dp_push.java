package algorithm_ds.chapter5;

import java.util.Arrays;
import java.util.Scanner;

public class Solution5_1_dp_push {
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

        for(int i = 0; i < N; i++) {
            if(i + 1 < N) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i] + Math.abs(arr[i] - arr[i + 1]));
            }
            if(i + 2 < N) {
                dp[i + 2] = Math.min(dp[i + 2], dp[i] + Math.abs(arr[i] - arr[i + 2]));
            }

        }



        answer = dp[N - 1];
        System.out.println(answer);
    }
}
