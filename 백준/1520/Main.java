import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author djunnni
 * dp[r][c]가 0일경우로 비교하게 되면 33%에서 시간초과
 * 왜 -1로 두어야 할까? 
 * 
 * 1. 이미 방문하지 못할 지점들을 잡아뒀는데 계속 4방탐색을 통해 거쳐가는 케이스를 가지치기 해야 성공
 */
public class Main {
    public static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }; // 하, 우, 상, 좌
    public static int[][] matrix;
    public static int[][] dp;
    public static int M, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // M: 세로
        N = Integer.parseInt(st.nextToken()); // N: 가로

        matrix = new int[M][N];
        dp = new int[M][N];

        for(int r = 0; r < M; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                matrix[r][c] = Integer.parseInt(st.nextToken());
                dp[r][c] = -1;
            }
        }
        bw.write(Integer.toString(dfs(0, 0)));
        bw.flush();
        bw.close();
    }
    public static int dfs(int r, int c) {
        if(r == M - 1 && c == N - 1) {
            return 1;
        }

        if(dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 0;
        for(int i = 0; i < d.length; i++) {
            int nr = d[i][0] + r;
            int nc = d[i][1] + c;
            if(!isOver(nr, nc) && matrix[nr][nc] < matrix[r][c]) {
                dp[r][c] += dfs(nr, nc);
            }
        }

        return dp[r][c];
    }
    public static boolean isOver(int r, int c) {
        return r < 0 || c < 0 || r >= M || c >= N;
    }
}
