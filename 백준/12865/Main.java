import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
    평범한 배낭
    DP문제
    왜 DP로 할까? 브루트포스로는 접근 못하나? => 부분집합 문제로 접근하면? => 2 ^ 100 => X
    그리디로는 접근 못하나? => 가치가 높은 순으로 접근하면? , 부피대비 가치가 높은 순으로 접근하면? [?]
    DP => i번째 아이템을 선택하고 j의 무게일때 최대 가치를 저장하기
 */
public class Main {
    static int dp[][], w[], v[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 물품 수
        int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

        dp = new int[N + 1][K + 1];
        w = new int[N + 1];
        v = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= K; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= w[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
