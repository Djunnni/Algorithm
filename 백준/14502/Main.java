import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_14502_연구소
 * author djunnni
 */
public class Main {
    static class Spot {
        int r, c;
        char type;

        Spot(int r, int c, char type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    static int N, M, wallCount, answer;
    static ArrayList<Spot> viruses;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 세로 N, 가로 M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wallCount = 3;
        // 원래있던 벽과 별개로 벽을 3개를 더 세울 수 있다.
        char[][] map = new char[N][M];

        viruses = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            String data = br.readLine();
            for (int c = 0, index = 0; c < M; c++, index += 2) {
                map[r][c] = data.charAt(index);
                if (map[r][c] == '2')
                    viruses.add(new Spot(r, c, map[r][c]));
            }
        }
        // map 출력하기
        // for(char[] row : map) {
        // System.out.println(Arrays.toString(row));
        // }
        answer = 0;

        go(map, 0);

        System.out.println(answer);
    }

    private static void go(char[][] map, int cnt) {
        if (cnt == wallCount) { // 벽을 3개 새웠으면 바이러스 전파를 시작한다.
            spreadVirus(map);
            int result = getLifeArea(map);
            if (answer < result)
                answer = result;
            return;
        }
        char[][] newMap = new char[N][M];
        for (int nr = 0; nr < N; nr++) {
            for (int nc = 0; nc < M; nc++) {
                if (map[nr][nc] == '0') { // r, c가 빈칸이라면, 놓는다.
                    clone(map, newMap);
                    newMap[nr][nc] = '1';
                    go(newMap, cnt + 1);
                }
            }
        }
    }

    private static int getLifeArea(char[][] map) {
        int sum = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == '0') {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static void spreadVirus(char[][] map) {
        Queue<Spot> queue = new ArrayDeque<>();
        for (Spot virus : viruses) {
            queue.offer(virus);
        }

        while (!queue.isEmpty()) {
            Spot virus = queue.poll();

            for (int d = 0; d < dr.length; d++) {
                int nr = virus.r + dr[d];
                int nc = virus.c + dc[d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == '0') {
                    map[nr][nc] = '2';
                    queue.add(new Spot(nr, nc, map[nr][nc]));
                }

            }
        }
    }

    private static void clone(char[][] original, char[][] copy) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                copy[r][c] = original[r][c];
            }
        }
    }
}
