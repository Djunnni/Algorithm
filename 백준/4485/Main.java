import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ_4485_녹색옷입은애가젤다지?
 * author djunnni
 */
public class Main {
    public static int dr[] = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
    public static int dc[] = { 0, 0, -1, 1 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = 1;
        String input = null;
        while((input = br.readLine()) != null) {
            // 동굴의 크기를 나타내는 N (2 <= N <= 125)
            int N = Integer.parseInt(input);
            // N이 0이면 종료
            if(N == 0) break;

            // 루피 지도
            int map[][] = new int[N][N];

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 루피 지도
//            for(int row[] : map) {
//                System.out.println(Arrays.toString(row));
//            }
//            System.out.println("================");

            // D[i][j]는 시작점에서부터 D[i][j]까지 가는데 필요한 루피를 말합니다.
            int D[][] = new int[N][N];
            // 전부 거기를 INF로 초기화
            for(int[] row : D) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            // 처음 진입점은 시작루피로 변경
            D[0][0] = map[0][0];

            Queue<int []> queue = new ArrayDeque<>();
            queue.add(new int[] { 0, 0 });

            while(!queue.isEmpty()) {
                // [0] -> r, [1] -> c
                int spot[] = queue.poll();

                for(int i = 0; i < dr.length; i++) {
                    int nr = spot[0] + dr[i];
                    int nc = spot[1] + dc[i];

                    if(!isOver(N, nr, nc) && D[nr][nc] > D[spot[0]][spot[1]] + map[nr][nc]) {
                        D[nr][nc] = D[spot[0]][spot[1]] + map[nr][nc];
                        queue.add(new int[] { nr, nc });
                    }
                }
            }


            sb.append("Problem ").append(tc++).append(": ").append(D[N-1][N-1]).append("\n");
        }
        System.out.println(sb);
    }
    public static boolean isOver(int SIZE, int r, int c) {
        if(r < 0 || c < 0 || r >= SIZE || c >= SIZE) {
            return true;
        }
        return false;
    }
}
