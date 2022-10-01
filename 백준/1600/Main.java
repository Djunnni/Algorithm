import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_1600_말이_되고픈_원숭이
 * author djunnni
 */
public class Main {
    static char[][] map;
    static int[][][] dp;
    static int H, W, K; // W: 너비, H: 높이
    static int dx[] = { -1, 1, 0, 0 }, dy[] = { 0, 0, -1, 1 }; // 좌, 우, 상, 하
    // TODO: 필요시 말 점프 추가
    static int hourseDx[] = { 1, 2, 1, 2, -1, -2, -1, -2 }, hourseDy[] = { 2, 1, -2, -1, -2, -1, 2, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        // 지도 데이터 넣기
        for (int i = 0; i < H; i++) {
            String data = br.readLine();
            for (int j = 0, index = 0; j < W; index += 2, j++) {
                map[i][j] = data.charAt(index);
            }
        }
        // 출력
        // for(char[] row : map) {
        // System.out.println(Arrays.toString(row));
        // }
        /**
         * dp는 (i,j)에서 K번의 점프를 진행한다 했을 때, 넘어갈 수 있는 수다.
         * 이유: k번 점프를 한다해도 k-1번에서 더 작은 거리로 갈 수 있을 것 같다.
         */
        dp = new int[H][W][K + 1];
        for (int[][] row : dp) {
            for (int[] ks : row)
                Arrays.fill(ks, Integer.MAX_VALUE);
        }
        // 시작점 초기화 진행
        for (int i = 0; i <= K; i++) {
            dp[0][0][i] = 0;
        }

        gaja(0, 0, 0);

        // 마지막에서 K번째까지 루프를 돌아서 정답 추출하기
        int rowPath = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            if (rowPath > dp[H - 1][W - 1][i]) {
                rowPath = dp[H - 1][W - 1][i];
            }
        }

        System.out.println(rowPath == Integer.MAX_VALUE ? -1 : rowPath);
    }

    private static void gaja(int startY, int startX, int startK) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { startY, startX, startK });

        while (!queue.isEmpty()) {
            int[] spot = queue.poll();
            int y = spot[0];
            int x = spot[1];
            int k = spot[2];
            // 일반 원숭이 점프
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isOver(ny, nx) && !isWall(ny, nx) && dp[ny][nx][k] > dp[y][x][k] + 1) {
                    dp[ny][nx][k] = dp[y][x][k] + 1;
                    queue.add(new int[] { ny, nx, k });
                }

            }
            // 말 점프
            for (int i = 0; i < hourseDx.length && k < K; i++) {
                int nx = x + hourseDx[i];
                int ny = y + hourseDy[i];

                if (!isOver(ny, nx) && !isWall(ny, nx) && dp[ny][nx][k + 1] > dp[y][x][k] + 1) {
                    dp[ny][nx][k + 1] = dp[y][x][k] + 1;
                    queue.add(new int[] { ny, nx, k + 1 });
                }
            }
        }

    }

    private static boolean isWall(int y, int x) {
        return map[y][x] == '1';
    }

    private static boolean isOver(int y, int x) {
        if (y >= H || x >= W || x < 0 || y < 0) {
            return true;
        }
        return false;
    }
}
