import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_11657_타임머신
 * author djunnni
 *
 * 접근방법
 * 벨만-포드 방식(cost가 음수 포함)
 * VE => 3,000,000
 * 예상시간 0.03초
 */
public class Main {
    public static int matrix[][];
    public static long dist[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시의 개수 1 <= N <= 500
        int N = Integer.parseInt(st.nextToken());
        // 버스 노선의 개수 1 <= M <= 6000
        int M = Integer.parseInt(st.nextToken());

        // N개의 도시들이 있는 지도 표시
        matrix = new int[N + 1][N + 1];
        for (int[] row : matrix) {
            Arrays.fill(row, 10001); // C가 0인경우가 쓰이고 있어서 가장 큰값 다음을 골랐다. [Point 1]
        }
        // 버스 노선별 정보
        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            // from에서 to로가는데 cost 비용이 든다.
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            matrix[from][to] = Math.min(matrix[from][to], cost); // cost가 가장 작은 애가 갈 수 있도록 변경 [Point 2]
        }
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        boolean exist_nagative_cycle = false;

        dist = new long[N + 1]; // underflow나 overflow가 발생할 수 있는 가능성 [Point 3]
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        for (int iter = 1; iter <= N; iter++) {
            boolean update = false;
            for (int v = 1; v <= N; ++v) {
                if (dist[v] == Long.MAX_VALUE) {
                    continue;
                }

                for (int i = 1; i <= N; i++) {
                    if (matrix[v][i] == 10001)
                        continue;
                    if (dist[i] > dist[v] + matrix[v][i]) {
                        dist[i] = dist[v] + matrix[v][i];
                        update = true;
                    }
                }
            }
            if (!update)
                break;
            if (iter == N && update)
                exist_nagative_cycle = true;
        }

        if (exist_nagative_cycle) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                System.out.println(dist[i] == Long.MAX_VALUE ? -1 : dist[i]);
            }
        }
    }
}
