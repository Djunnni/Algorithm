import java.util.Scanner;

/**
 * BJ_11053_가장_긴_증가하는_부분_수열
 * 1sec, 256mb
 * author djunnni
 */
public class Main {
    public static int MAX;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int arr[] = new int[N + 1];
        int dp[] = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        for(int i = 1; i <= N; i++) {
            dp[i] = 1;

            for(int j = 1; j <= i; j++) {
                if(arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int max = 0;
        for(int i = 1; i <= N; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
/**
 * 9
 * 10 20 30 5 6 7 80 9 10
 * => 5 [ 5, 6, 7, 9, 10]
 *
 * 6
 * 10 20 10 30 20 50
 * => 4 [10, 20, 30, 50]
 *
 * 5
 * 3 2 1 2 3
 * => 3 [1, 2, 3]
 *
 * 3
 * 3 2 1
 * => 1 [1] [2] [3]
 */