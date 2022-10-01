package swexpert.SW_1249;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * swexpert.SW_1249_보급로
 * author djunnni
 */
public class Solution {
    static int matrix[][], map[][], N;
    static int dx[] = { 1, -1, 0, 0 }, dy[] = { 0, 0, -1, 1 }; // 우, 좌, 상, 하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());

            matrix = new int[N][N];
            map = new int[N][N];

            // 지도 데이터 추가
            for (int i = 0; i < N; i++) {
                String[] data = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(data[j]);
                }
            }
            // 지도 출력
            // for(int [] row : matrix) {
            // System.out.println(Arrays.toString(row));
            // }

            // MAX로 변경
            for (int[] row : map) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }
            // 이완
            map[0][0] = matrix[0][0];

            // 동작
            recovery(0, 0);

            System.out.println("#" + tc + " " + map[N - 1][N - 1]);
        }
    }

    private static void recovery(int y, int x) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { y, x });

        while (!queue.isEmpty()) {
            int[] spot = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = spot[1] + dx[i];
                int ny = spot[0] + dy[i];

                if (!isOver(ny, nx) && map[ny][nx] > matrix[ny][nx] + map[spot[0]][spot[1]]) {
                    map[ny][nx] = matrix[ny][nx] + map[spot[0]][spot[1]];
                    queue.add(new int[] { ny, nx });
                }

            }

        }
    }

    private static boolean isOver(int y, int x) {
        if (y >= N || x >= N || x < 0 || y < 0) {
            return true;
        }
        return false;
    }
}
