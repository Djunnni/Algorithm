import java.util.*;
import java.io.*;

/**
 * @author djunnni
 */
public class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 작업장 수 N

        int[][] workTime = new int[N + 1][2]; // 0 => A, 1 => B
        int[][] moveEachTime = new int[N + 1][2]; // [i][j] => j가 0일 때, A[i]번째 작업장에서 B[i + 1]으로 옮길 때, 들어가는 비용 j가 1일때는 반대
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            workTime[i][0] = Integer.parseInt(st.nextToken());
            workTime[i][1] = Integer.parseInt(st.nextToken());
            if(i < N) {
                moveEachTime[i][0] = Integer.parseInt(st.nextToken());
                moveEachTime[i][1] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N + 1][2]; // n번째 작업까지 가는데 걸린 최소 시간
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = dp[0][1] = 0;
        dp[1][0] = workTime[1][0];
        dp[1][1] = workTime[1][1];

        for(int i = 2; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + moveEachTime[i - 1][1]) + workTime[i][0];
            dp[i][1] = Math.min(dp[i - 1][0] + moveEachTime[i - 1][0], dp[i - 1][1]) + workTime[i][1];
        }


        System.out.println(Math.min(dp[N][0], dp[N][1]));
    }
}