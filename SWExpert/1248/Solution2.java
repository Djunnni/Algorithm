import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 공통조상
 * JAVA 20sec, 256MB
 * [풀이방법]
 * 자식용 리스트, 부모용 리스트를 만들어
 * 공통 부모를 찾고 서브트리를 재귀적으로 탐색합니다.
 * @Author djunnni
 */
public class Solution2 {
    static List<Integer>[] map;
    static List<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine()); // 테스트케이스 수

        for(int tc = 1; tc <= TC; tc++) { // 테스트케이스 수만큼 반복하기
            sb.append("#").append(tc).append(" ");

            st = new StringTokenizer(br.readLine(), " ");

            int V = Integer.parseInt(st.nextToken()); // 정점의 개수
            int E = Integer.parseInt(st.nextToken()); // 간선의 개수

            map = new List[V + 1]; // 해당맵은 자식 i에게 연결된 부모 리스트입니다.
            tree = new List[V + 1]; // 트리의 정보를 저장합니다.
            for (int i = 1; i <= V; i++) {
                map[i] = new LinkedList<>();
                tree[i] = new LinkedList<>();
            }

            int node1 = Integer.parseInt(st.nextToken()); // 노드 1
            int node2 = Integer.parseInt(st.nextToken()); // 노드 2

            st = new StringTokenizer(br.readLine(), " "); // 부모자식간의 연결관계 읽기

            for (int e = 1; e <= E; e++) { // E개 만큼의 부모와 자식관계를 저장합니다.
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                map[child].add(parent); // 자식입장에서 부모를 저장합니다!
                tree[parent].add(child);
            }

            List<Integer> node1Parents = getParentList(node1);
            List<Integer> node2Parents = getParentList(node2);

            int lastSameParent = getLastSameParent(node1Parents, node2Parents);

            sb.append(lastSameParent).append(" ").append(getSubTreeSize(lastSameParent)).append("\n");
        }
        System.out.println(sb);
    }

    private static int getSubTreeSize(int lastSameParent) {
        int size = 1; // lastSameParent의 개수 1을 더해둡니다.

        List<Integer> subTree = tree[lastSameParent];
        for(int node : subTree) {
            size += getSubTreeSize(node);

        }
        return size;
    }

    private static int getLastSameParent(List<Integer> node1Parents, List<Integer> node2Parents) {
        Iterator<Integer> node1ParentsIterator = node1Parents.iterator();
        Iterator<Integer> node2ParentsIterator = node2Parents.iterator();

        int lastSameParent = 0; // 0으로 초기화
        while(node1ParentsIterator.hasNext() && node2ParentsIterator.hasNext()) {
            int n1 = node1ParentsIterator.next();
            int n2 = node2ParentsIterator.next();
            if(n1 != n2) { // 두 부모가 다르다면 END
                break;
            }
            lastSameParent = n1;
        }
        return lastSameParent;
    }
    private static List<Integer> getParentList(int node) {
        LinkedList<Integer> temp = new LinkedList<>();

        List<Integer> parents = map[node];
        while(!parents.isEmpty()) {
            int p = parents.get(0);
            temp.addFirst(p);
            parents = map[p];
        }

        return temp;
    }
}
