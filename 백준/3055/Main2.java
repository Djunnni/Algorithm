import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_3055_탈출
 * author djunnni
 * 
 * 풀이법 : BFS를 통해 탐색을 진행
 * 1초마다 물과 고슴도치의 이동 가능성을 체크
 */
public class Main2 {
    static char[][] map;
    static int R, C;
    static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        ArrayList<int[]> waters = new ArrayList<>();
        int[] dochi = new int[2];

        for (int i = 0; i < R; i++) {
            String data = br.readLine();
            for (int j = 0, index = 0; j < C; j++, index++) {
                map[i][j] = data.charAt(index);
                if (map[i][j] == '*') { // 물
                    waters.add(new int[] { i, j });
                } else if (map[i][j] == 'S') {
                    dochi[0] = i;
                    dochi[1] = j;
                    map[i][j] = '.';
                }
            }
        }

        int answer = BFS(dochi, waters);
        System.out.println(answer == -1 ? "KAKTUS" : answer);
    }

    private static int BFS(int[] dochi, ArrayList<int[]> waters) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean visited[][] = new boolean[R][C];

        visited[dochi[0]][dochi[1]] = true;
        queue.add(dochi);

        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            ArrayList<int[]> temp = new ArrayList<>();
            for (int[] water : waters) {
                for (int i = 0; i < dr.length; i++) {
                    int nr = water[0] + dr[i];
                    int nc = water[1] + dc[i];
                    // 범위를 벗어나거나 방문했거나 돌이라면 continue;
                    if (isOver(nr, nc) || map[nr][nc] == 'D' || map[nr][nc] == 'X' || map[nr][nc] == '*')
                        continue;

                    map[nr][nc] = '*';
                    temp.add(new int[] { nr, nc });
                }
            }

            while (size-- > 0) {
                int[] point = queue.poll();

                for (int i = 0; i < dr.length; i++) {
                    int nr = point[0] + dr[i];
                    int nc = point[1] + dc[i];
                    // 범위를 벗어나거나 방문했거나 돌이라면 continue;
                    if (isOver(nr, nc) || visited[nr][nc] || map[nr][nc] == 'X' || map[nr][nc] == '*')
                        continue;

                    if (map[nr][nc] == 'D')
                        return count;
                    if (map[nr][nc] == '.') {
                        visited[nr][nc] = true;
                        queue.offer(new int[] { nr, nc });
                    }
                }
            }
            waters = temp;
        }

        return -1;
    }

    private static boolean isOver(int r, int c) {
        if (r < 0 || c < 0 || r >= R || c >= C)
            return true;
        return false;
    }
}
