import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ_1654_랜선자르기
 * author djunnni
 */
public class Main {
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 이미 가지고 있는 랜선의 개수 1 <= k <= 10,000
        int K = Integer.parseInt(st.nextToken());
        // 필요한 랜선의 개수 1 <= n <= 1,000,000
        N = Integer.parseInt(st.nextToken());

        // 가지고 있는 파이프들의 길이 배열
        int pipe[] = new int[K];

        // 그중에서 최대 길이의 파이프를 찾는다.
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < K; i++) {
            pipe[i] = Integer.parseInt(br.readLine());
            if(max < pipe[i]) { // 최대 업데이트
                max = pipe[i];
            }
        }

        // 가지고 있는 파이프 확인하기
//        System.out.println(Arrays.toString(pipe));
//        System.out.println("====================");

        System.out.println(findLan(pipe, max));
    }
    public static long findLan(int[] pipe, int end) {
        long low = 1;
        long high = end;

        while(low <= high) {
            int count = 0;
            long mid = (low + high) / 2;

            for(int i = 0; i < pipe.length; i++) {
                count += pipe[i] / mid;
            }

            if(count >= N) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return high;
    }
}
