import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 파핑파핑_지뢰찾기
 * 소요시간 1시간 30분
 * 문제점: [1] dx,dy 사용부분에서 시간을 낭비 => 2중 for문 사용
 * character 기반을 int로 전환해 . => -1, * => 2로 치환
 * 8방 탐색을 돌되 가장 best 케이스는 8방이 모두 폭탄이 없는 곳이 최선이라고 생각함
 * 그 이후에 -1의 개수를 세어 문제를 해결
 */
public class Solution {
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

            Queue<int[]> dots = new LinkedList<>(); // 최대 생겨도 N^2의 개수
            for(int i = 0; i < N; i++) { // N행의 저장 데이터를 matrix로 넣기
                char[] row = br.readLine().toCharArray();
                for(int j = 0; j < row.length; j++) {
                    if(row[j] == '.') { // 지뢰가 없는 칸일경우, dots로 저장한다.
                        dots.add(new int[] {i, j});
                        matrix[i][j] = -1;
                    } else if(row[j] == '*') {
                        matrix[i][j] = -2;
                    }

                }
            }

            int answer = 0;
            while(!dots.isEmpty()) {
                int[] spot = dots.poll(); // { x, y } 지점
                if(matrix[spot[0]][spot[1]] == -1 && isAllZero(spot[0], spot[1])) { // 아직 증명이 되지 않은 공간이라면!
                    answer++; // 몇번이 발생하는지 정답의 개수를 더하자.

                    // 지점으로부터 8방 탐색을 돈다.
                   traversal(spot[0], spot[1]);

//                    for(int i = 0; i < N; i++) {
//                        System.out.println(Arrays.toString(matrix[i]));
//                    }
//                    System.out.println("========");
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

        while(!queue.isEmpty()) {
            int[] spot = queue.poll();
            if(matrix[spot[0]][spot[1]] != -1) {
                continue;
            }
            int count = 0;
            LinkedList<int[]> tempQueue = new LinkedList<>();
            for(int i = 0; i < dx.length; i++) {
                int nx = spot[0] + dx[i];
                int ny = spot[1] + dy[i];

                if(isOver(nx,ny)) continue;
                if(isMime(nx,ny)) count++;
                else {
                    tempQueue.add(new int[] {nx, ny});
                }
            }
            matrix[spot[0]][spot[1]] = count;
            if(count == 0) {
                queue.addAll(tempQueue);
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