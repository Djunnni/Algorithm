import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_1865_웜홀
 * author djunnni
 */
public class Main {
    public static void main(String[] args) {
        // BufferedReader의 AutoClosable Test
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // TC의 개수
            int TC = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int testCase = 1; testCase <= TC; testCase++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                // 지점의 수 N / 1 ~ 500
                int N = Integer.parseInt(st.nextToken());
                // 도로의 수 M / 1 ~ 2500
                int M = Integer.parseInt(st.nextToken());
                // 웜홀의 개수 W / 1 ~ 200
                int W = Integer.parseInt(st.nextToken());

                int matrix[][] = new int[N + 1][N + 1];
                for (int[] row : matrix) {
                    Arrays.fill(row, 10001);
                }
                // 도로의 정보
                for (int m = 1; m <= M; m++) {
                    st = new StringTokenizer(br.readLine(), " ");
                    // S, E 연결된 지점 번호
                    int S = Integer.parseInt(st.nextToken());
                    int E = Integer.parseInt(st.nextToken());
                    // T 이동하는 비용
                    int T = Integer.parseInt(st.nextToken());

                    matrix[S][E] = matrix[E][S] = Math.min(matrix[E][S], T);
                }

                // 웜홀의 정보
                for (int w = 1; w <= W; w++) {
                    st = new StringTokenizer(br.readLine(), " ");
                    // S -> E
                    int S = Integer.parseInt(st.nextToken());
                    int E = Integer.parseInt(st.nextToken());
                    // T 줄어드는 시간
                    int T = Integer.parseInt(st.nextToken());

                    matrix[S][E] = Math.min(matrix[S][E], -T);
                }

                long dist[] = new long[N + 1];
                Arrays.fill(dist, Integer.MAX_VALUE);

                dist[1] = 0;
                boolean has_cycle = false;

                for (int iter = 1; iter <= N; iter++) {
                    boolean update = false;
                    for (int v = 1; v <= N; v++) {

                        for (int j = 1; j <= N; j++) {
                            if (matrix[v][j] == 10001)
                                continue;

                            if (dist[j] > dist[v] + matrix[v][j]) {
                                update = true;
                                dist[j] = dist[v] + matrix[v][j];
                            }
                        }
                    }

                    if (!update)
                        continue;
                    if (iter == N && update)
                        has_cycle = true;
                }

                if (has_cycle)
                    sb.append("YES\n");
                else
                    sb.append("NO\n");
            }
            System.out.print(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
