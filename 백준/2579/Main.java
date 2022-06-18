import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = sc.nextInt();
        }
        int dp[] = new int[N + 1];

        System.out.println(func(arr, dp, N));
    }
    static public int func(int[] arr, int[] dp, int N) {
        if(N < 1) {
            return 0;
        }
        if(dp[N] == 0) {
            dp[N] = Math.max(func(arr, dp, N - 2), func(arr, dp, N - 3) + arr[N - 1]) + arr[N];
        }
        return dp[N];
    }
}