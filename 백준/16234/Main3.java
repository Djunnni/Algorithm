import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author djunnni
 * 인구 이동
 * 앞전과 비교해 union-find를 제거하고 순간을 돌면서 반영할 수 있으면 인구 이동을 실시.
 * 304540KB	1272ms
 */
public class Main {
    public static int dx[] = { -1, 1, 0, 0 }; // 좌, 우, 상, 하
    public static int dy[] = { 0, 0, -1, 1 };
    public static int matrix[][];
    public static int MAX, LOW, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N^2
        LOW = Integer.parseInt(st.nextToken()); // 최소 값
        MAX = Integer.parseInt(st.nextToken()); // 최대 값

        matrix = initialMatrix(br);

        int turn = 0;
        while(playMove()) {
            turn++;
        }
        System.out.println(turn);
    }
    /**
     * 인구이동을 수행합니다.
     */
    public static boolean playMove() {
        // 4방 탐색 시작 => 같이 열려있다면 map에 최소의 값으로 업데이트하자.
       boolean[][] visited = new boolean[N][N];

       boolean canMove = false;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(visited[r][c]) continue;
                List<int[]> nodes = dfs(visited, r, c);

                int sum = nodes.stream().mapToInt(d -> matrix[d[0]][d[1]]).sum();
                int size = nodes.size();

                if(sum != matrix[r][c]) {
                    canMove = true;
                }
                nodes.stream().forEach(d -> {
                    matrix[d[0]][d[1]] = Math.round(sum / size);
                });
            }
        }

        return canMove;
    }

    private static List<int[]> dfs(boolean[][] visited, int r, int c) {
        List<int[]> part = new LinkedList<>();
        if(visited[r][c]) return part;

        visited[r][c] = true;
        part.add(new int[] { r, c });
        for(int i = 0; i < dx.length; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if(isOver(nr, nc) || visited[nr][nc]) continue;
            int v = Math.abs(matrix[nr][nc] - matrix[r][c]);
            if(v <= MAX && v >= LOW) {
                List<int[]> nextPart = dfs(visited, nr, nc);
                part.addAll(nextPart);
            }
        }

        return part;
    }
    public static boolean isOver(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }

    /**
     * 맵을 초기화 합니다.
     */
    public static int[][] initialMatrix(BufferedReader br) throws IOException {
        int[][] map = new int[N][N];
        for(int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        return map;
    }
}
