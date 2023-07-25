package solved.ac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Djunnni
 * DFS를 올바르게 적용하자.. 동준아
 */
public class Main_16234 {
    public static int dx[] = { -1, 1, 0, 0 }; // 좌, 우, 상, 하
    public static int dy[] = { 0, 0, -1, 1 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // N^2
        int L = Integer.parseInt(st.nextToken()); // 최소 값
        int R = Integer.parseInt(st.nextToken()); // 최대 값

        Map<Integer, Integer> matrix = initialMatrix(br, N);

        int turn = 0;
        while(playMove(matrix, L, R, N)) {
            turn++;
        }
        System.out.println(turn);
    }
    /**
     * 인구이동을 수행합니다.
     */
    public static boolean playMove(Map<Integer, Integer> matrix, int low, int max, int n) {
        // 연합을 구하기
        Map<Integer, Integer> map = new HashMap<>(); // 자기 자신을 바라보도록 세팅하기
        for(int key : matrix.keySet()) {
            map.put(key, key);
        }
        // 4방 탐색 시작 => 같이 열려있다면 map에 최소의 값으로 업데이트하자.
        Map<Integer, Boolean> visited = new HashMap<>();
        for(int r = 1; r <= n; r++) {
            for(int c = 1; c <= n; c++) {
                if(visited.getOrDefault(getKey(r, c), false)) {
                    continue;
                }
                dfs(matrix, visited, map, low, max, r, c, n);
            }
        }
        // 연합이 하나도 없다면 return false;
        if(new HashSet<Integer>(map.values()).size() == (int) Math.pow(n, 2)) {
            return false;
        }
        // 인구이동 하기
        Map<Integer, List<Integer>> moveMap = new HashMap<>();
        for(int key : map.keySet()) {
            moveMap.put(key, new LinkedList<>());
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            moveMap.get(entry.getValue()).add(entry.getKey());
        }
        for(Map.Entry<Integer, List<Integer>> entry : moveMap.entrySet()) {
            int sum = entry.getValue().stream().mapToInt(i -> matrix.get(i)).sum();
            int size = entry.getValue().size();

            for(int spot : entry.getValue()) {
                matrix.put(spot, Math.round(sum / size));
            }
        }

        return true;
    }

    private static void dfs(Map<Integer, Integer> matrix, Map<Integer, Boolean> visited, Map<Integer, Integer> map, int low, int max, int r, int c, int n) {
        visited.put(getKey(r, c), true);

        for(int i = 0; i < dx.length; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if(isOver(n, nr, nc) || visited.getOrDefault(getKey(nr, nc),false)) continue;
            int v = Math.abs(matrix.get(getKey(nr, nc)) - matrix.get(getKey(r, c)));
            if(v <= max && v >= low) {
                union(map, getKey(nr, nc), getKey(r, c));
                dfs(matrix, visited, map, low, max, nr, nc, n);
            }
        }
    }

    public static int find(Map<Integer, Integer> map, int v) {
        if(map.get(v) == v) {
            return v;
        }
        int result = find(map, map.get(v));
        map.put(v, result);
        return result;
    }
    public static void union(Map<Integer, Integer> map, int a, int b) {
        a = find(map, a);
        b = find(map, b);

        if(a >= b) {
            map.put(a, b);
        } else {
            map.put(b, a);
        }
    }
    public static boolean isOver(int n, int r, int c) {
        return r <= 0 || c <= 0 || r > n || c > n;
    }

    /**
     * 맵을 초기화 합니다.
     */
    public static Map<Integer, Integer> initialMatrix(BufferedReader br, int N) throws IOException {
        Map<Integer, Integer> matrix = new HashMap<>();
        for(int r = 1; r <= N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= N; c++) {
                matrix.put(getKey(r, c), Integer.parseInt(st.nextToken()));
            }
        }
        return matrix;
    }

    /**
     * 맵의 키를 조회합니다.
     */
    public static int getKey(int r, int c) {
        return r * 100 + c;
    }
}
