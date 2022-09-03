import java.util.Scanner;

/**
 * acmicpc.BJ_2579_계단_오르기
 * author djunnni
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 계단의 수
        int N = sc.nextInt();
        // 계단별 높이 저장
        int[] steps = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            steps[i] = sc.nextInt();
        }

        // 해당 높이까지 올라갈 최대 계단 높이를 저장합니다.
        int[] dp = new int[N + 1];

        /**
         * 한 번에 한, 두계단씩 오를 수 있다.
         * 연속된 세 개의 계단을 모두 밟아선 안된다. 시작점은 계단에 포함되지 않는다.
         * 마지막 도착계단은 반드시 밟아야 한다.
         *
         * 연속된 세계단을 체크할 변수가 필요하다. or 세번째에서 바로 올라올 수 있게
         * dp[i] = dp[i - 3] + steps[i - 1] + steps[i]
         * dp[i] = dp[i - 2] + steps[i] 다.
         */
        // System.out.println(Arrays.toString(dp));
        // N - 1번째 계단을 오를 때, 얻을 수 있는 최댓값을 출력한다.
        System.out.println(stepUp(steps, dp, N));
    }

    // 앞에서 뒤의 계단을 본다.
    public static int stepUp(int[] steps, int[] dp, int N) {
        if (N < 1) {
            return 0;
        }

        if (dp[N] == 0) {
            dp[N] = Math.max(stepUp(steps, dp, N - 2), stepUp(steps, dp, N - 3) + steps[N - 1]) + steps[N];
        }

        return dp[N];
    }
}
