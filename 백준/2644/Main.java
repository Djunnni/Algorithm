import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_2644_촌수계산
 * DFS 문제로 범위 실수 조심하기
 * author djunnni
 */
public class Main {
    static boolean finish, jockbo[][], visited[][];
    static int answer, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사람수
        N = Integer.parseInt(br.readLine());

        // 족보
        jockbo = new boolean[N + 1][N + 1];
        // 방문처리 체크
        visited = new boolean[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 촌수를 구해야하는 사람의 번호가 나온다.
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 관계의 개수 M
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            jockbo[from][to] = true;
            jockbo[to][from] = true;
        }
        // 못간다고 가정하기
        answer = -1;
        // 탐색 시작
        dfs(A, B, 0);

        System.out.println(answer);
    }

    public static void dfs(int from, int dest, int count) {
        if (from == dest) {
            answer = count;
            finish = true;
            return;
        }
        for (int i = 1; i <= N && !finish; i++) {
            if (jockbo[from][i] && !visited[from][i] && !visited[i][from]) {
                visited[from][i] = true;
                visited[i][from] = true;
                dfs(i, dest, count + 1);
            }
        }
    }
}
