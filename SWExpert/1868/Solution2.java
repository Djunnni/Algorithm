import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 앞전대비 queue를 진정 사용해야 하는가를 고민해보면 필요가 없다. => 8방탐색으로 결국 제외시키므로 => 제거하기
 * 
 */
public class Solution2 {
    static int matrix[][];
    static int N;
    static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 }; // 왼위, 왼중, 왼아, 중위, 중아, 오위, 오중, 오아
    static int[] dy = { 1, 0, -1, 1, -1, 1, 0, -1 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트케이스 수 T

        for(int tc = 1; tc <= T; tc++) { // 테스트 케이스 수 만큼 반복한다.
            N = Integer.parseInt(br.readLine()); // N x N 지뢰찾기 표 크기
            matrix = new int[N][N];

            for(int i = 0; i < N; i++) { // N행의 저장 데이터를 matrix로 넣기
                char[] row = br.readLine().toCharArray();
                for(int j = 0; j < row.length; j++) {
                    if(row[j] == '.') { // 지뢰가 없는 칸일경우, dots로 저장한다.
                        matrix[i][j] = -1;
                    } else if(row[j] == '*') {
                        matrix[i][j] = -2;
                    }

                }
            }

            int answer = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(matrix[i][j] != -1) continue;
                    if(isAllZero(i, j)) {
                        answer++;
                        traversal(i, j);
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(matrix[i][j] == -1) answer++;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
    private static boolean isAllZero(int x, int y) {
        for(int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isOver(nx,ny)) continue;
            if(isMime(nx,ny)) return false;
        }
        return true;
    }
    private static void traversal(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        matrix[x][y] = 0;

        while(!queue.isEmpty()) {
            int[] spot = queue.poll();
            matrix[spot[0]][spot[1]] = 0;
            for(int i = 0; i < dx.length; i++) {
                int nx = spot[0] + dx[i];
                int ny = spot[1] + dy[i];

                if (isOver(nx, ny) || matrix[nx][ny] != -1) continue;
                if(isAllZero(nx, ny)){ // 8방 돌아서도 전부 제로면 배열에 넣고 0으로 초기화 => 숫자를 만드는게 목표 아님
                    queue.add(new int[]{nx, ny});
                }
                matrix[nx][ny] = 0;
            }
        }
    }
    public static boolean isMime(int x, int y) {
        return matrix[x][y] == -2;
    }
    public static boolean isOver(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= N) return true;
        return false;
    }
}