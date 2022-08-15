package acmicpc.BJ_1912;

import java.util.Scanner;

/**
 * BJ_1912_연속합
 * 1초 128MB
 *
 * 풀이방법
 * n^2로 완전탐색을 돌게 되면 100억 연산이 나와 10초 이상이 걸린다.
 * 즉, 다른방법이 필요한 상황
 *
 * author djunnni
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int arr[] = new int[N];
        int dp[] = new int[N];
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            if(i == 0) {
                dp[i] = arr[i];
            } else {
                dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            }
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }
}

