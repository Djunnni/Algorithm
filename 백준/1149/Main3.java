import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_1149
 * RGB거리
 * @author djunnni
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 집 N개

        int colorBook[][] = new int[N][3];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            colorBook[i][0] = Integer.parseInt(st.nextToken()); // R
            colorBook[i][1] = Integer.parseInt(st.nextToken()); // G
            colorBook[i][2] = Integer.parseInt(st.nextToken()); // B
        }

        /**
         * 1, 2, 4, 5는 모두 서로가 같은색이면 안된다.
         * 2, 3, 4는 같은 색이면 안된다.
         * 결국은 모든게 같은 색이면 안된다.
         */
        int dp[][] = new int[N][3];
        dp[0][0] = colorBook[0][0];
        dp[0][1] = colorBook[0][1];
        dp[0][2] = colorBook[0][2];

        for(int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + colorBook[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + colorBook[i][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + colorBook[i][2];
        }

        System.out.println(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])));
    }
}
