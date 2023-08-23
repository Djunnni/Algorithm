import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Prim
 * => PQ로 접근시 O(ElogV)
 */
public class PrimPQ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수

        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int i = 1; i <= V; i++) {
            map.put(i, new ArrayList<>());
        }

        for(int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map.get(start).add(new int[] { end, weight });
            map.get(end).add(new int[] { start, weight });
        }

        int answer = 0;
        boolean[] visited = new boolean[V + 1];
        int routeCount = 0;
        // 0번에서부터 출발한다고 가정하자
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        queue.add(new int[] { 1, 0 });

        while(!queue.isEmpty()) {
            int current[] = queue.poll();
            if(visited[current[0]]) continue;
            visited[current[0]] = true;
            answer += current[1];
            if(++routeCount == V) break;


            for(int[] data : map.get(current[0])) {
                if(visited[data[0]]) continue;
                queue.add(new int[] { data[0], data[1] });
            }
        }

        System.out.println(answer);
    }
}
