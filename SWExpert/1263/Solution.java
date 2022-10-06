package swexpert.D6_1263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * swexpert.D6_1263_사람네트워크2
 * author djunnni
 * 1TC, 2초, 256MB
 * 1000 * 1000 * 1000 => 10억 => 1초
 * 플루이드워셜을 쓸 문제(그래프)
 */
public class Solution {
    static int matrix[][], dist[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            // N: 사람 수 (1000 이하)
            int N = Integer.parseInt(st.nextToken());

            matrix = new int[N + 1][N + 1];

            // matrix는 1~N의 범위로 연결
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 출력 확인
            // for(int [] row: matrix) {
            // System.out.println(Arrays.toString(row));
            // }
            dist = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j)
                        dist[i][j] = 0;
                    else if (matrix[i][j] > 0)
                        dist[i][j] = matrix[i][j];
                    else
                        dist[i][j] = 500_000;
                }
            }

            for (int k = 1; k <= N; k++) { // 경유지
                for (int i = 1; i <= N; i++) { // 출발지
                    for (int j = 1; j <= N; j++) { // 도착지
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                int[] row = dist[i];
                min = Math.min(Arrays.stream(row).sum(), min);
            }

            System.out.println("#" + tc + " " + min);
        }
    }
}
