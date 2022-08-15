import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * AtCoder_DP_Contest_C_Vacation
 *
 * 2sec, 1024MB
 *
 * author djunnni
 */
public class Practice5_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 크기 N + 1 * 3
        // i번 열 -> 특정 날
        // 0 -> swim, 1 -> catch, 2 -> homework
        int[][] days = new int[N + 1][3];
        int[][] dp = new int[N + 1][3];

        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                days[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= N; i++) {
                dp[i][0] = days[i][0] + Math.max(dp[i -1][2], dp[i - 1][1]);
                dp[i][1] = days[i][1] + Math.max(dp[i -1][0], dp[i - 1][2]);
                dp[i][2] = days[i][2] + Math.max(dp[i -1][1], dp[i - 1][0]);
        }

        int answer = 0;
        for(int i = 0; i < 3; i++) {
            if(answer < dp[N][i]) {
                answer = dp[N][i];
            }
        }
        System.out.println(answer);

    }
}
