import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Prim
 * => 배열로 접근 시, 메모리 초과 [400MB]
 */
public class PrimMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수

        int[][] matrix = new int[V + 1][V + 1];
        for(int[] row: matrix) {
           Arrays.fill(row, Integer.MAX_VALUE);
        }

        for(int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            matrix[end][start] = matrix[start][end] = weight;
        }

        int answer = 0;
        boolean[] visited = new boolean[V + 1];
        int routeCount = 0;
        // 0번에서부터 출발한다고 가정하자
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while(!queue.isEmpty()) {
            int current = queue.poll();
            if(visited[current]) continue;
            visited[current] = true;

            int min = Integer.MAX_VALUE;
            int spot = -1;
            for(int v = 1; v <= V; v++) {
                if(visited[v]) continue;
                if(matrix[current][v] < min) { // 가장 최소지점으로 변경
                    min = matrix[current][v];
                    spot = v;
                }
            }
            answer += matrix[current][spot];
            queue.add(spot);
            routeCount++;
            if(routeCount == V - 1) break;
        }

        System.out.println(answer);
    }
}
