import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, 1, -1 }; ; // 상, 하, 우, 좌
    static int[][] matrix;
    static boolean[][] visited;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j=0; j < M; j++) {
                matrix[i][j] = s.charAt(j) - '0';
            }
        }

        visited[0][0] = true;
        trace(0, 0);

        System.out.println(matrix[N-1][M-1]);
    }
    // BFS로 구현이 필요하다.
    public static void trace(int x, int y) {
        Queue<Integer> queueX = new LinkedList<>();
        Queue<Integer> queueY = new LinkedList<>();
        queueX.add(x);
        queueY.add(y);

        while(!queueX.isEmpty()) {
            int q_x = queueX.poll();
            int q_y = queueY.poll();
            for (int i = 0; i < dx.length; i++) {
                int nextX = q_x + dx[i];
                int nextY = q_y + dy[i];

                if (isOver(nextX, nextY) || isWall(nextX, nextY) || isVisited(nextX, nextY)) {
                    continue;
                }
                matrix[nextX][nextY] = matrix[q_x][q_y] + 1;
                visited[nextX][nextY] = true;
                queueX.add(nextX);
                queueY.add(nextY);
            }
        }
    }
    public static boolean isWall(int x, int y) {
        if(matrix[x][y] == 0) {
            return true;
        }
        return false;
    }
    public static boolean isOver(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= M) {
            return true;
        }
        return false;
    }
    public static boolean isVisited(int x, int y) {
        return visited[x][y];
    }
}
