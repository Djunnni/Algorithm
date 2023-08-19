import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BJ_1922
 * 네트워크 연결
 * @author djunnni
 * 모든 지점이 하나씩은 연결되어 있으니 아무 지점에서 시작해도 상관없다.
 * 짧은 거리순으로 모두 queue에 넣고 작은 거리를 방문했는지 여부로 길이를 더해간다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 컴퓨터 수
        int M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수

        int[][] matrix = new int[N][N];
        for(int[] row : matrix) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 양방향 길 중 최소값으로 변경
            matrix[start - 1][end - 1] = Math.min(matrix[start - 1][end - 1], cost);
            matrix[end - 1][start - 1] = Math.min(matrix[end - 1][start - 1], cost);
        }

        int answer = Integer.MAX_VALUE;
        // i부터 N까지 최소로 방문하며 가장짝은 답을 구한다.
        boolean[] visited = new boolean[N];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[1], b[1]);
        });
        queue.add(new int[] { 0, 0 });

        int value = 0;
        while(!queue.isEmpty()) {
            int spot[] = queue.poll();
            if(visited[spot[0]]) continue;

            visited[spot[0]] = true;
            value += spot[1];

            for(int j = 0; j < N; j++) {
                if(matrix[spot[0]][j] != Integer.MAX_VALUE) {
                    queue.add(new int[] { j, matrix[spot[0]][j] });
                }
            }
        }
        answer = Math.min(answer, value);


        System.out.println(answer);
    }
}
