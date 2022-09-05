import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_1149_RGB거리
 * author djunnni
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 집 N개
        int N = Integer.parseInt(br.readLine());

        // 빨강, 파랑, 초록 중 하나로 칠해야 한다.
        // 집 별로 가격을 미리 받아둔다.
        int colors[][] = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                colors[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int dp[][] = new int[N + 1][3];
        for (int row[] : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // i는 현재 칠하는 컬러
        for (int i = 0; i <= 2; i++) {
            paintColor(colors, dp, N, i);
        }
        // 결과 확인
        // for(int row[] : dp) {
        // System.out.println(Arrays.toString(row));
        // }
        // 각 집마다 마지막에 위치하는건 색상이 구분되는 지점
        int cost = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
        System.out.println(cost);
    }

    // color는 현재 칠하는 컬러입니다.
    public static int paintColor(int[][] colors, int[][] dp, int N, int color) {
        if (N < 1) {
            return 0;
        }
        if (dp[N][color] == Integer.MAX_VALUE) {
            for (int i = 0; i < 3; i++) {
                if (color == i)
                    continue;
                dp[N][color] = Math.min(dp[N][color], paintColor(colors, dp, N - 1, i) + colors[N][color]);
            }
        }
        return dp[N][color];
    }
}
