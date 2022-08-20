import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * BJ_1914_하노이_탑
 * 6초, 128MB
 * author djunnni
 */
public class Main {
    static StringBuffer sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 원판의 개수 N
        int N = Integer.parseInt(br.readLine());
        sb = new StringBuffer();

        BigInteger k = new BigInteger("2").pow(N).subtract(BigInteger.ONE);
        sb.append(k).append("\n");
        if(N <= 20) {
            // 하노이의 탑은 N번을 1번에서 시작하고 임시 공간을 2로 목적지는 3을 향한다.
            hanoi(N, 1, 2, 3);
        }
        System.out.println(sb);
    }
    private static void hanoi(int n, int start, int temp, int end) {
        if(n == 1) {
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }

        hanoi(n - 1, start, end, temp); // n - 1개를 temp로 이동시킨다.
        sb.append(start).append(" ").append(end).append("\n");
        hanoi(n - 1, temp, start, end); // n -1개를 temp에서 end로 다시 보낸다.
    }
}
