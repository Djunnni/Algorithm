import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_10971_외판원순회2
 * 2초 256MB
 * 
 * 왜 난 다익스트라나 프림으로 접근하려 했는가?
 * 1. 도시간에 이동하는데 최소비용을 구하고 마지막 장소에서 출발점으로 돌아가면 정답이 될 줄 알았다.
 * 2. 이 환상이 깨지게 된 이유는 첫째 최소신장트리는 최단경로를 찾는 것이지 최적의 경로를 찾는게 아니기 때문이다.
 * 3. 다익스트라로 접근 할 때, 깨지는 이유중 또하나는 최단의 경로를 찾다보니 진입한 곳의 다음지점을 찾기보다 그 전에서 
 * 최단거리라면 그곳으로 연결되는 문제도 있다.
 * 
 * 순전히 DFS로 접근해야 한다.
 * 
 * author djunnni
 */
public class Main {
    static int W[][];
    static boolean[] visited;
    static int answer, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도시의 수 (2 <= N <= 10)
        N = Integer.parseInt(br.readLine());

        // 인접행렬 W[가중치]
        W = new int[N][N]; // 도시는 1번부터 수행

        // 행렬 성분은 1,000,000 이하의 정수 0인경우 갈 수 없는 길.
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            visited = new boolean[N];
            // start, from, sum, count
            dfs(i, i, 0, 0);
        }

        System.out.println(answer);
    }
    public static void dfs(int start, int from, int sum, int count) {
        if(sum >= answer) {
            return;
        }
        if(count == N && start == from) {
            answer = Math.min(sum, answer);
            return;
        }

        for(int to = 0; to < N; to++) {
            if(W[from][to] == 0 || visited[to]) continue;

            visited[to] = true;
            dfs(start, to, sum + W[from][to], count + 1);
            visited[to] = false;
        }
    }
}
