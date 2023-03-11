package algorithm.expert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 프로세서_연결하기
 * 연결되는 카운트를 세지 않고 최장길이를 구하려다보니 예외케이스가 발생
 * 최대한 많은 전선에 연결되었을 때, 최장길이 ! [여기가 핵심]
 *
 */
public class Solution {
    static int N, answer, maxCnt;
    static char[][] matrix;
    static List<int[]> maxinos;
    static int dx[] = { 1, -1, 0, 0 }; // 우, 좌, 상, 하
    static int dy[] = { 0, 0, -1, 1 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트케이스 T

        for(int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");

            N = Integer.parseInt(br.readLine()); // N 개 행,열

            matrix = new char[N][N]; // N x N 매트릭스
            maxinos = new ArrayList<>(12); // 맥시노스 리스트

            answer = 0;
            maxCnt = 0;

            for(int i = 0; i < N; i++) {
                String[] line = br.readLine().split(" ");
                for(int j = 0; j < line.length; j += 1) {
                    matrix[i][j] = line[j].charAt(0);
                    if(matrix[i][j] == '1' && !isLastLine(i, j)) {
                        maxinos.add(new int[] { i, j });
                    }
                }
            }

            getMinimumRoute(0, 0, 0);

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static void getMinimumRoute(int index, int result, int cnt) {
        if(index >= maxinos.size()) {
            if(cnt > maxCnt) {
                maxCnt = cnt;
                answer = result;
            } else if(cnt == maxCnt) {
                answer = Math.min(answer, result);
            }
            return;
        }
        int[] maxino = maxinos.get(index);
        for(int i = 0; i < dx.length; i++) { // 방향

            int wireLen = extend(maxino, i);
            if(wireLen == -1) {
                continue;
            }
            getMinimumRoute(index + 1, result + wireLen, cnt + 1);
            rollback(maxino, i);
        }
        getMinimumRoute(index + 1, result, cnt);
    }
    private static int extend(int[] maxino, int i) {
        int x = maxino[0] + dx[i], y = maxino[1] + dy[i];
        int len = 0;
        while(!isOver(x,y)) {
            if(matrix[x][y] != '0') {
                return -1;
            }
            x += dx[i];
            y += dy[i];
        }
        x = maxino[0] + dx[i];
        y = maxino[1] + dy[i];
        while(!isOver(x, y)) {
            matrix[x][y] = '*';
            x += dx[i];
            y += dy[i];
            len++;
        }
        return len;
    }
    private static void rollback(int[] maxino, int i) {
        int x = maxino[0] + dx[i], y = maxino[1] + dy[i];
        while(!isOver(x, y)) {
            matrix[x][y] = '0';
            x += dx[i];
            y += dy[i];
        }
    }
    public static boolean isLastLine(int x, int y) {
        if(x == 0 || y == 0 || x == N - 1 || y == N - 1) return true;
        return false;
    }
    public static boolean isOver(int x, int y) {
        if(x < 0 || y < 0 || x >= N || y >= N) return true;
        return false;
    }
}