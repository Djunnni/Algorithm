import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_1932_정수_삼각형
 * author djunnni
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int arr[] = new int[(N * (N + 1)) / 2];

        int idx = 0;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < i; j++) {
                arr[idx++] = Integer.parseInt(st.nextToken());
            }
        }

        // 배열 출력
        // System.out.println(Arrays.toString(arr));

        int dp[] = new int[arr.length];
        dp[0] = arr[0];
        idx = 0;

        for (int h = 0; h < N; h++) { // h는 높이
            for (int j = 0; j < h; j++) {
                dp[h + idx] = Math.max(dp[h + idx], dp[idx] + arr[h + idx]);
                dp[h + idx + 1] = Math.max(dp[h + idx + 1], dp[idx] + arr[h + idx + 1]);
                idx++;
            }
        }

        idx = ((N - 1) * N) / 2;
        int max = 0;
        for (int i = idx; i < arr.length; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
