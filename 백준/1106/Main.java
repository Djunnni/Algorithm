package solved.ac._1106;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author djunnni
 * C와 형택이가 홍보할 도시 개수 N개가 주어진다.
 * 형택이는 최소한의 고객 C명을 늘이기위해 투자해야되는 돈의 최소값을 구해라
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringTokenizer st = new StringTokenizer(sc.nextLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int dp[] = new int[C + 101];
        Arrays.fill(dp, 100000000);
        dp[0] = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(sc.nextLine());

            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            /**
             * 풀 수 있는 방법
             * 1. dp[people][cost] -> dp[C][0 ~ 1000] 가장 작은 수 찾기 -> 답을 뭐로 줄지 못함
             * 2. dp[people] = cost -> dp[C] 가장작은 값 찾기
             * 3. dp[cost] = people -> 테이블을 만들고 people에 해당하는 구간을 O(N)을 탐색해야해서 최 마지막 보루
             */

            // dp[5]: dp[5] dp[0], dp[6]: dp[6] dp[1]
            for(int j = people; j < C + 101; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - people]);
            }
        }

        int result = Integer.MAX_VALUE;

        for(int i = C; i < C + 101; i++) {
            result = Math.min(result, dp[i]);
        }
        // C명으로 늘이기까지 형택이가 투자한 돈의 최소값
        System.out.println(result);
    }
}
