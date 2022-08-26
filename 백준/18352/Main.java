import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BJ_18352_특정거리의도시찾기
 * 2sec, 256MB
 *
 * 풀이 방법
 * 1. 모든 도시 중의 최단 거리가 K임을 찾으므로 시작지점에서 전부를 확인해볼 수 있는 Dijkstra를 사용한다.
 * 2. 크기가 30만, 간선만 100만이므로 인접행렬로 접근하기보다 인접 리스트를 사용해보자.
 * 3. PriorityQueue를 이용해서 풀어본다.
 * author djunnni
 */
public class Main {
    public static class Node {
        int vertex;
        int weight;
        Node next;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public Node(int vertex, int weight, Node next) {
            this(vertex, weight);
            this.next = next;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 도시의 개수 (2 <= N <= 300,000)
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수 (2 <= M <= 1,000,000)
        int K = Integer.parseInt(st.nextToken()); // 거리의 정보 (1 <= K <= 300,000)
        int X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호 (1 <= K <= N)

        Node[] adjList = new Node[N + 1]; // 1번부터 처리예정

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            // start와 to는 서로다른 수다.
            int start = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[start] = new Node(to, 1, adjList[start]);
        }

        // start부터 모든 정점들까지의 거리
        int[] D = new int[N + 1];
        Arrays.fill(D, Integer.MAX_VALUE);

        // 집합에 속하는지 여부
        boolean[] visited = new boolean[N + 1];

        // 첫 방문지점 0으로 초기화
        D[X] = 0;

        // 거리를 오름차순으로 -> start지점에 더 가까운게 먼저!
        PriorityQueue<Node> pQueue = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.weight, b.weight)
        );

        pQueue.offer(new Node(X, D[X]));

        while(!pQueue.isEmpty()) {
            Node current = pQueue.poll();

            if(visited[current.vertex]) continue;

            visited[current.vertex] = true;

            // current가 경유지가 되게 처리
            for(Node temp = adjList[current.vertex]; temp != null; temp = temp.next) {
                if(!visited[temp.vertex] && D[temp.vertex] > D[current.vertex] + temp.weight) {
                    D[temp.vertex] = D[current.vertex] + temp.weight;
                    pQueue.add(new Node(temp.vertex, D[temp.vertex]));
                }
            }
        }

        boolean hasAnswer = false;
        for(int i = 1; i <= N; i++) {
            if(D[i] == K) {
                hasAnswer = true;
                System.out.println(i);
            }
        }
        if(!hasAnswer) {
            System.out.println(-1);
        }
    }
}
