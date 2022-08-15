import java.util.Arrays;
import java.util.Scanner;

public class Practice5_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W = sc.nextInt();

        int arr[] = new int[N + 1];
        boolean dp[][] = new boolean[N + 1][W + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        /**
         * i번째 요소를 선택할 때, 배열에 값과 같으면 그 값은 선택가능하므로 true
         * i + 1번째 요소부터는 이전에 선택된 i번째 요소가 true이며 배열값을 선택했을 때, W보다 작으면 true다.
         * 또한 선택하지 않는 경우도 true로 한다.
         */
        for(int i = 1; i <= N; i++) {
            for(int w = 0; w <= W; w++) {
                if(arr[i] == w) {
                    dp[i][w] = true;
                }
                if(i > 1) {
                    if(dp[i - 1][w] && w + arr[i] <= W) {
                        dp[i][w + arr[i]] = true;
                        dp[i][w] = true;
                    }
                }
            }
        }

        for(boolean x[] : dp) {
            System.out.println(Arrays.toString(x));
        }
        System.out.println(dp[N][W]);
    }
}
