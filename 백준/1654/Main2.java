package solved._1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 랜선 자르기
 * @author djunnni
 * end, start => mid를 구하는 데 있어 Integer 범위를 벗어날 수 있으니 long 설정하기..
 */
public class Main {
    static int K, N, line[];
    static long answer = Integer.MIN_VALUE; // K : 이미 있는 랜선, N 필요 랜선의 개수
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        initialize();
        solved();
        System.out.println(answer);
    }
    public static void solved() {
        long start = 1, end = line[K - 1]; // 최소와 최대
        while(start <= end) {
            long mid = (start + end) / 2;
            long cnt = 0;
            for(int i = 0; i < K; i++) {
                cnt += line[i] / mid;
            }
            if(cnt >= N) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        answer = end;


    }
    static void initialize() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        line = new int[K];
        for(int i = 0; i < K; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(line); // 어짜피 line은 계속 비교할 거 범위를 오름차순으로 정의해버리기
    }
}
