import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * D4_3124_최소스패닝트리_Prim
 * author djunnni
 */
public class Prim {
    public static class Node {
        int id;
        long weight;
        Node next;
        public Node(int id, long weight) {
            this.id = id;
            this.weight = weight;
        }
        public Node(int id, long weight, Node next) {
            this(id, weight);
            this.next = next;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder out = new StringBuilder();

        // 테스트케이스 수
        int T = Integer.parseInt(in.readLine());

        for(int tc = 1; tc <= T; tc++) {
            out.append("#").append(tc).append(" ");

            StringTokenizer tokens = new StringTokenizer(in.readLine(), " ");

            // 정점의 개수
            int V = Integer.parseInt(tokens.nextToken());
            // 간선의 개수
            int E = Integer.parseInt(tokens.nextToken());

            // 노드 i번째에 연결된 연결 리스트
            Node[] nodes = new Node[V + 1];

            for(int i = 0; i < E; i++) {
                tokens = new StringTokenizer(in.readLine(), " ");

                int from = Integer.parseInt(tokens.nextToken());
                int to = Integer.parseInt(tokens.nextToken());
                long weight = Long.parseLong(tokens.nextToken());

                // from과 to에 연결 정보 넣어주기
                nodes[from] = new Node(to, weight, nodes[from]);
                nodes[to] = new Node(from, weight, nodes[to]);
            }

            // Vertex들의 방문 Set을 확인하기 위함
            boolean visited[] = new boolean[V + 1];

            // Vertex들의 최소거리들을 저장합니다.
            long minEdge[] = new long[V + 1];
            Arrays.fill(minEdge, Integer.MAX_VALUE);

            // 첫번째 정점을 1로 시작
            minEdge[1] = 0;
            // 거리 값
            long result = 0;
            // 연결된 간선 수를 체크합니다.
            int count = 0;

            // 우선순위 큐를 도입합니다.
            // weight가 적은 순으로 나열하기
            PriorityQueue<Node> pQueue = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
            pQueue.offer(new Node(1, minEdge[1]));

            while(!pQueue.isEmpty()) {
                // 우선순위 큐에서 간선을 빼낸다.
                Node e = pQueue.poll();

                // 방문한 적이 있다면 넘어갑니다.
                if(visited[e.id]) continue;
                visited[e.id] = true;
                result += e.weight;
                // 카운트를 증가하고 V와 같다면 모든 정점을 연결한 것이다.
                if(++count == V) {
                    break;
                }

                for(Node node = nodes[e.id]; node != null; node = node.next) {
                    if(!visited[node.id] && minEdge[node.id] > node.weight) {
                        minEdge[node.id] = node.weight;
                        pQueue.offer(new Node(node.id, minEdge[node.id]));
                    }
                }
            }
            out.append(result).append("\n");
        }
        System.out.print(out);
    }
}
