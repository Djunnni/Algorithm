import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * acmicpc.BJ_2636_치즈
 * author djunnni
 */
public class Main {
    static char pizza[][];
    static int H, W, hour, cheezeCount;
    static boolean visited[][];
    static int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken()); // 세로 MAX 100
        W = Integer.parseInt(st.nextToken()); // 가로 MAX 100

        pizza = new char[H][W];

        hour = 0;
        int count = 0;
        // 피자 데이터 추가
        for (int i = 0; i < H; i++) {
            String data = br.readLine();
            for (int j = 0, index = 0; j < W; j++, index += 2) {
                pizza[i][j] = data.charAt(index);
                if (pizza[i][j] == '1') {
                    count++;
                }
            }
        }
        // 데이터 확인
        // for(char[] row : pizza) {
        // System.out.println(Arrays.toString(row));
        // }
        run(count);
        // 출력 : 치즈가 녹는데 걸리는 시간, 녹기 1시간 전 남은 치즈조각이 놓여있는 칸 개수
        System.out.printf("%d\n%d", hour, cheezeCount);
    }

    private static void run(int count) {
        ArrayList<int[]> airedEdges = new ArrayList<>();
        visited = new boolean[H][W];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { 0, 0 });
        visited[0][0] = true;

        hour = 0;
        do {
            hour++;
            while (!queue.isEmpty()) {
                int[] p = queue.poll();
                for (int i = 0; i < dr.length; i++) {
                    int nr = p[0] + dr[i];
                    int nc = p[1] + dc[i];

                    if (isOver(nr, nc) || visited[nr][nc]) {
                        continue;
                    }
                    if (pizza[nr][nc] == '1') {
                        pizza[nr][nc] = '2';
                        airedEdges.add(new int[] { nr, nc });
                    }
                    if (pizza[nr][nc] == '0') {
                        visited[nr][nc] = true;
                        queue.offer(new int[] { nr, nc });
                    }
                }
            }
            // 조각들을 반영
            if (airedEdges.size() > 0) {
                cheezeCount = airedEdges.size();
                count -= cheezeCount;
                for (int[] edge : airedEdges) {
                    pizza[edge[0]][edge[1]] = '0';
                    queue.offer(new int[] { edge[0], edge[1] });
                }
                airedEdges.clear();
            }
        } while (count > 0);
    }

    private static boolean isOver(int r, int c) {
        if (r < 0 || c < 0 || r >= H || c >= W) {
            return true;
        }
        return false;
    }
}
