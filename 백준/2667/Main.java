import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * acmicpc.BJ_2667_단지번호붙이기
 * author djunnni
 */
public class Main {
    public static char[][] map;
    public static ArrayList<Integer> danji;
    public static int N;
    public static int dr[] = { -1, 1, 0, 0 };
    public static int dc[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            char[] data = br.readLine().toCharArray();
            map[i] = data;
        }

        danji = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '1')
                    BFS(i, j);
            }
        }

        Collections.sort(danji);

        System.out.println(danji.size());

        for (int dan : danji) {
            System.out.println(dan);
        }
    }

    public static void BFS(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { r, c });
        map[r][c] = '*';
        int count = 1;
        while (!queue.isEmpty()) {
            int[] site = queue.poll();

            for (int i = 0; i < dr.length; i++) {
                int nextR = site[0] + dr[i];
                int nextC = site[1] + dc[i];

                if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < N && map[nextR][nextC] != '0'
                        && map[nextR][nextC] != '*') {
                    queue.add(new int[] { nextR, nextC });
                    map[nextR][nextC] = '*';
                    count++;
                }
            }
        }
        danji.add(count);
    }
}
