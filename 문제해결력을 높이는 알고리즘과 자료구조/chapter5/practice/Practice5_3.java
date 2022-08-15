import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Practice5_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W = N * 101;

        int arr[] = new int[N];
        boolean dp[][] = new boolean[N + 1][W + 1];
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        dp[0][0] = true;
        for(int i = 0; i < N; i++) {
            for(int w = 0; w <= W; w++) {
                if(dp[i][w]) {
                    dp[i + 1][w] = true;
                    dp[i + 1][w + arr[i]] = true;
                }
            }
        }

        int answer = 0;
        for(boolean x : dp[N]) {
            if(x) answer++;
        }
        System.out.println(answer);
    }
}
