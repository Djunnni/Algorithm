package chapter5_1;

import java.util.Arrays;
import java.util.Scanner;

public class Solution5_8 {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        String T = sc.next();

        int[][] dp = new int[S.length() + 1][T.length() + 1];
        for(int[] ar : dp) {
            Arrays.fill(ar, Integer.MAX_VALUE);
        }

        dp[0][0] = 0;

        for(int i = 0; i <= S.length(); i++) {
            for(int j = 0; j <= T.length(); j++) {
                // 대체
                if(i > 0 && j > 0) {
                    if(S.charAt(i - 1) == T.charAt(j - 1)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                    } else {
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + 1);
                    }
                }
                // 삭제
                if(i > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
                // 추가
                if(j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                }
            }
        }
        System.out.println(dp[S.length()][T.length()]);
    }
}
