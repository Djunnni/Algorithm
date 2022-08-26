import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BJ_1753_최단경로
 * 통과조건 1sec , 256MB
 *  
 * 접근방법
 * V -> 20,000, E -> 300,000
 * O(2V^2) -> 인접행렬 => 대략 8억 -> 8초 Over
 * O(V^2 + E) -> 4초 Over
 * O((V+E)logV) -> 1초 미만 -> 인접리스트 + PrioirtyQueue
 * author djunnni
 */
public class Main {
    public static class Node {
        int vertex;
        int weight;
        Node next;

        public Node(int vertex, int weight) {
            this.vertex =vertex;
            this.weight = weight;
        }
        public Node(int vertex, int weight, Node next) {
            this(vertex, weight);
            this.next = next;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        StringBuilder sb = new StringBuilder();

        // 정점의 개수 1<= V <= 20,000
        int V = Integer.parseInt(st.nextToken());
        // 간선의 개수 1<= E <= 300,000
        int E = Integer.parseInt(st.nextToken());

        // 시작 정점의 번호
        int K = Integer.parseInt(br.readLine());

        // 1 ~ V까지의 인접 리스트
        Node[] adjList = new Node[V + 1];

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // to까지 가는 가중치 간선을 저장
            adjList[from] = new Node(to, weight, adjList[from]);
        }

        // 정점까지의 최단 거리를 저장합니다.
        int distance[] = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // 방문여부
        boolean visited[] = new boolean[V + 1];

        // 시작 정점
        distance[K] = 0;

        PriorityQueue<Node> pQueue = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pQueue.offer(new Node(K, distance[K]));

        while(!pQueue.isEmpty()) {
            Node cur = pQueue.poll();

            if(visited[cur.vertex]) continue;

            visited[cur.vertex] = true;

            for(Node temp = adjList[cur.vertex]; temp != null; temp = temp.next) {
                if(!visited[temp.vertex] && distance[temp.vertex] > distance[cur.vertex] + temp.weight) {
                    distance[temp.vertex] = distance[cur.vertex] + temp.weight;
                    pQueue.add(new Node(temp.vertex, distance[temp.vertex]));
                }
            }
        }

        for(int i = 1; i <= V; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(distance[i]).append("\n");
            }
        }

        System.out.print(sb);
    }
}
