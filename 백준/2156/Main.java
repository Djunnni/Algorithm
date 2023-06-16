import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * 와인 마시기는 3번 연속으로 컵을 들어 마실 수 없다.
 * 그렇다면?
 * [1] [2] [3] [4]
 * [1] [2] [4]
 * [1] [3] [4]
 * [2] [3]
 * 왼쪽에서부터 무조건 마실 때, 자기자신을 뺀 나머지 dp[i]
 * 자기자신 전전까지 마시고 자기 더하기 dp[i - 2] + cups[i]
 * 자기자신 전전전까지 마시고 자신과 자신 전 더하기 dp[i - 3] + cups[i - 1] + cups[i]
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cups[] = new int[N];
        int dp[] = new int[N];

        for(int i = 0; i < N; i++) {
            cups[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = cups[0];
        if(N == 1) {
            System.out.println(dp[0]);
            return;
        }

        dp[1] = dp[0] + cups[1]; // 2잔 마실 수 있음
        if(N == 2) {
            System.out.println(dp[1]);
            return;
        }
        
        dp[2] = Math.max(dp[1], Math.max(dp[0] + cups[2], dp[1] + cups[2] - dp[0]));
        for(int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + cups[i], dp[i - 3] + cups[i - 1] + cups[i]));
        }

        System.out.println(dp[N - 1]);
    }
}
