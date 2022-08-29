### Kruskal 알고리즘

#### 크루스칼 알고리즘은?

    * 최소신장트리(MST)에서 간선중심의 연결로 거리의 비용 최소를 구하는 알고리즘 입니다.
    * 간선들을 통해 구하는데 그리디 접근을 하게 됩니다.
    (사이클이 만들어지지 않는 선에서 최소의 비용을 선택)

#### 시간복잡도

    O(Elog(E)) // E : 간선

#### 진행하기전에 알아두면 좋을 내용!

- 신장트리 : n개의 정점으로 이루어진 무향 그래프에서 n개의 정점을 n-1개의 간선만으로 연결된 트리
- 최소신장트리 : 무향 가중치 그래프에서 신장트리를 구성하는데 가중치의 합이 최소인 트리

#### 어떻게 만들 수 있나요?

    * 서로소 집합이 필요합니다.
    * 서로소 집합은 서로다른 집합간에 중복요소가 없는 집합을 의미합니다.
    * 이를 제작하는데는 union-find의 개념을 알아야 합나디.

    * 만약 union-find를 사용하지 않는다면 어떻게 찾아갈까요?
        * BFS, DFS를 이용한 탐색을 할텐데 시간이 오래걸릴것 같아요!

#### union-find는 어떻게 할 수 있나요?

아래 3단계로 구성됩니다.

    * make -> 각 원소들을 모두 자신만의 집합으로 만듭니다.
    * find -> 해당 원소의 루트(최상부모)를 찾습니다.
    * union -> 두 집합을 결합합니다.

```java
static void make() {
        parents = new int[V]; // 정점만큼 배열을 제작

        for(int i = 0; i < V; i++) {
            parents[i] = i; // 자신의 집합으로 초기화
        }
    }
    static int find(int a) {
        if(a == parents[a]) { // 부모와 찾으려는 요소가 같다면 return
            return a;
        }
        return parents[a] = find(parents[a]); // path compression
    }
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) { // 사이클
            return false;
        }

        if(aRoot > bRoot) { // aRoot를 작은쪽으로 하자.
            int temp = aRoot;
            aRoot = bRoot;
            bRoot = temp;
        }

        parents[bRoot] = aRoot; // 큰쪽을 작은걸로 변경
        return true;
    }
```

#### 크루스칼 로직

- union-find도 준비가 완료됐습니다.
- 이제 간선들을 오름차순으로 쭉 나열할 것입니다.
  > 간선들의 가중치가 오름차순이어야 최소들을 선택해 적은 비용이 나오니깐요 ㅎ

```java
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight; // 가중치의 오름차순
        }
    }

    Arrays.sort(edgeList); // 얻은 리스트를 정렬합니다.
```

- 이제 간선들을 돌아다니면서 선택해볼게요! 개수는 정점의 수 - 1 까지만 하면 됩니다!

```java
    int result = 0;
        int count = 0;
        for(Edge e : edgeList) {
            if(union(e.from, e.to)) {
                result += e.weight;
                count += 1;
                if(count == V - 1) break;
            }
        }
```

- 완료!
  ```java
  system.out.println(result);
  ```

### 최종코드

```java
package ssafy.daily0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * author djunnni
 */
public class KruskalTest {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }
    static int[] parents;
    static int V, E;
    static Edge[] edgeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList[i] = new Edge(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }
        make();
        Arrays.sort(edgeList);

        int result = 0;
        int count = 0;
        for(Edge e : edgeList) {
            if(union(e.from, e.to)) {
                result += e.weight;
                count += 1;
                if(count == V - 1) break;
            }
        }
        System.out.println(result);
    }
    static void make() {
        parents = new int[V];

        for(int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }
    static int find(int a) {
        if(a == parents[a]) {
            return a;
        }
        return parents[a] = find(parents[a]); // path compression
    }
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) { // 사이클이네
            return false;
        }

        if(aRoot > bRoot) { // aRoot를 작은쪽으로 하자.
            int temp = aRoot;
            aRoot = bRoot;
            bRoot = temp;
        }

        parents[bRoot] = aRoot; // 큰쪽을 작은걸로 변경
        return true;
    }
}

/** 테스트케이스 1
 *  7 11
    0 1 32
    0 2 31
    0 5 60
    0 6 51
    1 2 21
    2 4 46
    2 6 25
    3 4 34
    3 5 18
    4 5 40
    4 6 51
    => 175 // 결과
 */
```
