import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_1149_RGB거리
 * author djunnni
 */
public class Main2 {
    public static void main(String[] args) throws IOException {
        /**
         * 1번집의 색은 2번 집의 색과 같지 않아야한다.
         * N번 집의 색은 N-1번 집의 색과 같이 않아야한다.
         * i(2 <= i <= N - 1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야한다.
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 집의 수 2 <= N <= 1000
        int N = Integer.parseInt(br.readLine());

        // 집들마다 1번부터 시작하면서 빨강, 초록, 파랑 순으로 배열 인덱스 처리한다.
        int[][] map = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }

        // DP에 넣어보자
        int dp[][] = new int[N + 1][3];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][0] = dp[0][1] = dp[0][2] = 0;

        // i번째에 레드를 칠할 때는 i-1번째의 그린, 블루를 선택한 경우에서 더하기
        for (int i = 1; i <= N; i++) {
            dp[i][0] = Math.min(dp[i][0], Math.min(dp[i - 1][1], dp[i - 1][2]) + map[i][0]);
            dp[i][1] = Math.min(dp[i][1], Math.min(dp[i - 1][0], dp[i - 1][2]) + map[i][1]);
            dp[i][2] = Math.min(dp[i][2], Math.min(dp[i - 1][1], dp[i - 1][0]) + map[i][2]);
        }

        // 최종 점수들간 비교하기
        System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));

    }
}
