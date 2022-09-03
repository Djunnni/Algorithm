import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ_1463_1로_만들기
 * 
 * @author djunnni
 *
 */
public class Main2 {
    public static int dp[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        /**
         * N % 3 == 0 -> N /= 3 0, 1, 2
         * N % 2 == 0 -> N /= 2 0, 1
         * N - 1
         */
        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0; // 첫번째 숫자는 카운트 수가 0이다.
        // dp[i]번째 숫자는 dp[i+1]번째에 연산을 1번 한 경우다.

        findOne(0, N);

        System.out.println(dp[1]);
    }

    /**
     * 가지치기 전략으로 들어간다.
     * dp[i]는 숫자 i 일때, 들어갈 최소의 카운트 수를 기록합니다.
     * 즉, dp[i]는 i%3, i%2, i-1 하기 전의 숫자에 cnt + 1이다.
     * 
     * @param cnt
     * @param value
     */
    public static void findOne(int cnt, int value) {
        if (value < 0 || dp[value] < cnt) {
            return;
        }
        dp[value] = Math.min(dp[value], cnt);
        if (value == 1) {
            return;
        }

        if (value % 3 == 0) {
            findOne(dp[value] + 1, value / 3);
        }
        if (value % 2 == 0) {
            findOne(dp[value] + 1, value / 2);
        }
        findOne(dp[value] + 1, value - 1);
    }
}
