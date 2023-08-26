package solved._1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1238 파티
 * @author djunnni
 * 1 ~ N까지 X로 출발하고 X에서는 다익스트라 1번에 확인이 가능하다.
 * 즉, 다익스트라를 N번 수행하게 된다. (갈때 N - 1, 올때 1)
 */
public class Main {
    public static int[][] matrix;
    public static int N, M, X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // n명
        M = Integer.parseInt(st.nextToken()); // m개의 단방향
        X = Integer.parseInt(st.nextToken()); // 도착점

        matrix = new int[N + 1][N + 1];
        for(int row[] : matrix) {
            Arrays.fill(row, 1_000_000_000);
        }

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            matrix[start][end] = cost;
        }

        int[] costArr = new int[N + 1]; // 전체 요금
        // 풀이

        // N번 친구들이 가는데 걸리는 distance 구하기
        for(int i = 1; i <= N; i++) {
            if(i == X) continue;

            int distance[] = new int[N + 1];
            dijkstra(distance, i);
            costArr[i] = distance[X];
        }
        // X번에서 각자에게로 가는데 걸리는 시간 구하기
        int returnDistance[] = new int[N + 1];
        dijkstra(returnDistance, X);

        for(int i = 1; i <= N; i++) {
            if(i == X) continue;
            costArr[i] += returnDistance[i];
        }


        // 정답 출력
        int answer = Integer.MIN_VALUE;
        for(int cost : costArr) {
            if(answer < cost) answer = cost;
        }
        System.out.println(answer);
    }
    public static void dijkstra(int distance[], int start) {
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        for(int j = 1; j <= N; j++) {
            distance[j] = matrix[start][j];
        }

        for(int i = 1; i <= N; i++) {
            int current = getSmallIndex(visited, distance);
            visited[current] = true;
            for(int j = 1; j <= N; j++) {
                if(!visited[j] && distance[current] + matrix[current][j] < distance[j]) {
                    distance[j] = distance[current] + matrix[current][j];
                }
            }
        }

    }
    public static int getSmallIndex(boolean visited[], int[] distance) {
        int min = 1_000_000_000;
        int index = 0;
        for(int i = 1; i <= N; i++) {
            if(visited[i] || min < distance[i]) continue;
            min = distance[i];
            index = i;
        }
        return index;
    }
}
