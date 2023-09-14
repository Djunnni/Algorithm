package solved._14940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ_14940
 * 쉬운 최단거리
 * @author djunnni
 */
public class Main {
    public static int N, M;
    public static StringBuilder sb;
    public static int[][] direction = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} }; // 하, 상, 우, 좌
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        int matrix[][] = new int[N][M];
        int wantedSpot[] = new int[2];
        // 기본 값 넣기
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 2) { // 가고싶어하는 지점일 경우
                    wantedSpot[0] = i;
                    wantedSpot[1] = j;
                }
            }
        }

        int navigation[][] = bfs(matrix, wantedSpot);

        print(navigation, matrix);
    }
    // DFS로 풀게되면 시작점부터 모든 정점을 돌고 전점에서 모든 정점을 돌고 N^3 나오게 됨 -> 이문제는 BFS
    public static void dfs(int navigation[][], int[][] matrix, int y, int x, int current) {
        if(navigation[y][x] < current) {
            return;
        }
        for(int i = 0; i < 4; i++) {
            int ny = y + direction[i][0];
            int nx = x + direction[i][1];

            if(!isOver(ny, nx) && matrix[ny][nx] != 2 && matrix[ny][nx] != 0 && navigation[ny][nx] > current + matrix[ny][nx]) {
                navigation[ny][nx] = current + matrix[ny][nx];
                dfs(navigation, matrix, ny, nx, navigation[ny][nx]);
            }
        }
    }
    public static int[][] dfsWrapper(int[][] matrix, int[] wantedSpot) {
        int navigation[][] = new int[N][M];
        for(int[] row : navigation) { // 모든 지점을 최대값으로 변경해두기
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        navigation[wantedSpot[0]][wantedSpot[1]] = 0; // 시작점은 0

        dfs(navigation, matrix, wantedSpot[0], wantedSpot[1], 0);


        return navigation;
    }
    public static int[][] bfs(int[][] matrix, int[] wantedSpot) {
        // wantedSpot을 시작점이라고 보고 보든 점을 탐색한다. 탐색 시, 이미 방문한 지점이 더 작은 값이라면 더이상 가지 않아도 된다.
        int navigation[][] = new int[N][M];
        for(int[] row : navigation) { // 모든 지점을 최대값으로 변경해두기
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        navigation[wantedSpot[0]][wantedSpot[1]] = 0; // 시작점은 0
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { wantedSpot[0], wantedSpot[1], 0 });
        while(!queue.isEmpty()) {
            int spot[] = queue.poll();

            for(int i = 0; i < 4; i++) {
                int ny = spot[0] + direction[i][0];
                int nx = spot[1] + direction[i][1];

                if(!isOver(ny, nx) && matrix[ny][nx] != 2 && matrix[ny][nx] != 0 && navigation[ny][nx] > spot[2] + matrix[ny][nx]) {
                    navigation[ny][nx] = spot[2] + matrix[ny][nx];
                    queue.add(new int[] { ny, nx, navigation[ny][nx] });
                }
            }
        }

        return navigation;
    }
    public static void print(int[][] navigation, int[][] matrix) {
        for(int j = 0; j < navigation.length; j++) {
            int[] row = navigation[j];
            for(int i = 0; i < row.length; i++) {
                int value = row[i];
                if(row[i] == Integer.MAX_VALUE) {
                    if(matrix[j][i] > 0) value = -1;
                    else value = 0;
                }
                sb.append(value);
                if(i < row.length - 1) {
                    sb.append(" ");
                } else {
                    if(j != navigation.length - 1)
                        sb.append("\n");
                }
            }
        }
        System.out.print(sb);
    }
    public static boolean isOver(int ny, int nx) {
        return ny < 0 || nx < 0 || ny >= N || nx >= M;
    }
}
