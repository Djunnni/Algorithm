### Prim 알고리즘

#### Prim 알고리즘이란?
    * Prim 알고리즘은 MST(최소신장트리) 구현 방법중 하나로 정점을 중심으로 탐색을 합니다.
    * 탐색 방법은 출발 정점에서 다른 점들까지의 거리를 저장해두고 그 중 최소인 정점을 방문한 뒤, 방문한 곳을 기점으로 다른 점들까지 거리를 비교해 최소를 탐색하는 방식입니다.

    즉, 단순하게 말하자면 방문할 때마다 모든 정점의 거리를 가지고 있고 그중 최소를 선택해 반복적으로 진행해 최종 목적지에 도달하는 것입니다.

#### Prim 시간 복잡도
    * 인접행렬로 구현 시,
        * O(2v^2)
    * 인접리스트로 구현 시,
        * O(v^2 + E)
        * O((V + E)logV) // 힙(우선순위 큐) 사용 시

#### 진행하기전에 알아두면 좋을 내용!

    * 항상 프림으로 우선순위 큐를 쓴다고 장점만 있는 것은 아니다.
    * 정점과 간선을 확인하고 그래프의 특성을 봐라. 또한 계산을 해보고 최적을 이용하자.

#### 어떻게 만들까?

    * 정점을 기준으로 구하기에 Node 리스트가 필요하다.
    * Node 클래스에는 to[갈 정보], weight[가중치] 가 저장되며, 인접리스트로 제작해서 from의 정보는 배열의 index로 알 수 있다.
    ```java
        static class Node {
            int vertex, weight;
            Node next;

            public Node(int vertex, int weight, Node next) {
                this.vertex =vertex;
                this.weight =weight;
                this.next = next;
            }
        }

        Node[] adjList = new Node[V]; // 각 정점별 개수

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 무향 처리
            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(from, weight, adjList[to]);
    }
    ```
    * 정점에서 간선들의 정보를 알고있어야 하며, 방문여부도 체크해야 한다.
    ```java
        int[] minEdge = new int[V]; // 각 정점 입장에서 신장트리에 포함된 정점과의 간선 비용중 최소비용
        boolean[] visited = new boolean[V]; // 신장트리 포함여부

        Arrays.fill(minEdge, Integer.MAX_VALUE); // 최소값을 관리하기 위해 큰 값 세팅

    ```
    * 정점들 돌면서 해당 정점에서 최소 거리에 있는 다른 정점을 찾아 방문을 하고 가중치를 더한다.
    * 방문하게 된 정점에서 다른 정점으로 어딜 다닐지 체크한다.


#### 전체 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * author djunnni
 */
public class PrimTest {
    static class Node {
        int vertex, weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex =vertex;
            this.weight =weight;
            this.next = next;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Node[] adjList = new Node[V]; // 각 정점별 개수

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 무향 처리
            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(from, weight, adjList[to]);
        }

        // 프림 알고리즘에 필요한 자료구조
        int[] minEdge = new int[V]; // 각 정점 입장에서 신장트리에 포함된 정점과의 간선 비용중 최소비용
        boolean[] visited = new boolean[V]; // 신장트리 포함여부

        Arrays.fill(minEdge, Integer.MAX_VALUE); // 최소값을 관리하기 위해 큰 값 세팅

        // 1. 임의의 시작점 처리, 0번 정점을 신장트리 구성의 시작점
        minEdge[0] = 0;

        int result = 0; // 최소 신장트리 비용 누적

        for(int c = 0; c < V; c++) { // v개의 정점 처리하면 끝
            // step1. 신장트리의 구성에 포함되지 않은 정점 중 최소비용의 정점 선택
            int min = Integer.MAX_VALUE;
            int minVertex = -1;
            for(int i = 0; i < V; i++) {
                if(!visited[i] && min > minEdge[i]) {
                    min = minEdge[i];
                    minVertex = i;
                }
            }

            // step2. 신장트리 추가
            visited[minVertex] = true;
            result += min;

            // step3. 신장트리에 새롭게 추가되는 정점과 신장트리에 포함되지 않는 정점들의 기존 최소비용과 비교해서 갱신
            //          신장트리에 새롭게 추가되는 정점의 모든 인접 정점들을 돌아다니며 처리
            for(Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
                if(!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
                    minEdge[temp.vertex] = temp.weight;
                }
            }
        }

        System.out.println(result);
    }
}

```


### 우선순위 큐를 이용한 Prim

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * author djunnni
 */
public class PrimPriorityQueueTest {
    static class Node {
        int vertex, weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex =vertex;
            this.weight =weight;
            this.next = next;
        }
    }
    static class Vertex {
        int no, weight;

        public Vertex(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Node[] adjList = new Node[V]; // 각 정점별 개수

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 무향 처리
            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(from, weight, adjList[to]);
        }

        // 프림 알고리즘에 필요한 자료구조
        int[] minEdge = new int[V]; // 각 정점 입장에서 신장트리에 포함된 정점과의 간선 비용중 최소비용
        boolean[] visited = new boolean[V]; // 신장트리 포함여부

        Arrays.fill(minEdge, Integer.MAX_VALUE); // 최소값을 관리하기 위해 큰 값 세팅

        // 1. 임의의 시작점 처리, 0번 정점을 신장트리 구성의 시작점
        minEdge[0] = 0;

        int result = 0; // 최소 신장트리 비용 누적

        // 모든 부호가 양수라는 통일성이 있으면 빼고, 부호 변화가 있다면 Integer.compare 쓰기
        PriorityQueue<Vertex> pQueue = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
        pQueue.offer(new Vertex(0, minEdge[0]));

        int cnt = 0;
        while(!pQueue.isEmpty()) { // v개의 정점 처리하면 끝
            // step1. 신장트리의 구성에 포함되지 않은 정점 중 최소비용의 정점 선택
            Vertex minVertex = pQueue.poll();

            //////////////// 변경 ////////////////
            if(visited[minVertex.no]) continue;

            // step2. 신장트리 추가
            visited[minVertex.no] = true;
            result += minVertex.weight;
            if(++cnt == V) break;

            // step3. 신장트리에 새롭게 추가되는 정점과 신장트리에 포함되지 않는 정점들의 기존 최소비용과 비교해서 갱신
            //          신장트리에 새롭게 추가되는 정점의 모든 인접 정점들을 돌아다니며 처리
            for(Node temp = adjList[minVertex.no]; temp != null; temp = temp.next) {
                if(!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
                    minEdge[temp.vertex] = temp.weight;
                    pQueue.offer(new Vertex(temp.vertex,  minEdge[temp.vertex]));
                }
            }
        }

        System.out.println(result);
    }
}

```