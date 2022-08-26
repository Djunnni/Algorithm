package acmicpc.BJ_10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

/**
 * BJ_10026_적록색약
 * 1sec, 128MB
 * author djunnni
 */
public class Main {
    static char[][] matrix;
    static boolean[][] visited;
    static int[] colors;
    static int N;
    static int dx[] = {-1, 1, 0, 0}; // 좌, 우, 상, 하
    static int dy[] = { 0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // NxN 사이즈 크기
        N = Integer.parseInt(br.readLine());

        // 2차원 배열 초기화
        matrix = new char[N][N];
        visited = new boolean[N][N];

        // 데이터 넣기
        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < N; j++) {
                matrix[i][j] = input.charAt(j);
            }
        }

        colors = new int[3];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j]) continue;

                if(matrix[i][j] == 'G') {
                    colors[0]++;
                } else if(matrix[i][j] == 'R') {
                    colors[1]++;
                } else if(matrix[i][j] == 'B') {
                    colors[2]++;
                }
                DFS(i, j);
            }
        }

        int noMan = Arrays.stream(colors).sum();

        Arrays.fill(colors, 0);
        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(matrix[i][j] == 'G') matrix[i][j] = 'R';
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j]) continue;

                if(matrix[i][j] == 'G') {
                    colors[0]++;
                } else if(matrix[i][j] == 'R') {
                    colors[1]++;
                } else if(matrix[i][j] == 'B') {
                    colors[2]++;
                }
                DFS(i, j);
            }
        }

        int yesMan = Arrays.stream(colors).sum();

        System.out.println(noMan + " " + yesMan);
    }

    private static void DFS(int i, int j) {
        visited[i][j] = true;
        for(int d = 0; d < dx.length; d++) {
            int nr = i + dy[d];
            int nc = j + dx[d];

            if(nr < 0 || nr >= N || nc < 0 || nc >= N ||
                    visited[nr][nc] ||
                    matrix[nr][nc] != matrix[i][j]
            ) {
                continue;
            }
            DFS(nr, nc);
        }
    }
}
