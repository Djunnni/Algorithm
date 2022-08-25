import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ_1916_최소비용_구하기
 * 0.5초 128MB
 *
 * 인접행렬 -> V^2 -> 1백만 * 2 => 2백만정도면 0.05초 0.1초 가능으로 보임.
 * 
 * 예외케이스가 너무 다양했다.
 * 
 * 첫번째, 버스비용은 0보다 크거나 같다 -> 거리비용을 0으로 초기화한 상태로 시작하면 안됨.
 * 같은 A,B구간에 weight가 여러번 들어갈 수 있다 -> 최소인 것만 넣기
 * 
 * 지문외에도 의문이 생길 수 있는 것들을 캐치해서 풀자.
 * 
 * author djunnni
 */
public class Main {
    static int[][] adjMatrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        // 도시의 개수 (1 <= N <= 1000)
        int N = Integer.parseInt(br.readLine());

        // 인접행렬 크기
        adjMatrix = new int[N + 1][N + 1];
        for(int[] row : adjMatrix) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for(int i = 1; i <= N; i++) {
            adjMatrix[i][i] = 0;
        }
        // 버스의 개수
        int M = Integer.parseInt(br.readLine());

        for(int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjMatrix[from][to] = Math.min(adjMatrix[from][to], weight);
        }

        st = new StringTokenizer(br.readLine(), " ");
        // 시작지점
        int start = Integer.parseInt(st.nextToken());
        // 도착지점
        int end = Integer.parseInt(st.nextToken());

        // D까지 가는데 걸린 시간을 저장
        long D[] = new long[N + 1];
        Arrays.fill(D, Integer.MAX_VALUE);
        D[start] = adjMatrix[start][start]; // 첫 시작지점은 0으로 변경

        // 집합에 포함 여부
        boolean visited[] = new boolean[N + 1];

        int minVertex;
        long min;
        for(int i = 1; i <= N; i++) {
            min = Integer.MAX_VALUE;
            minVertex = -1;
            // step1. 시작지점에서 가장 근처의 간선들중 가까운데 찾기
            for(int j = 1; j <= N; j++) {
                if(!visited[j] && min > D[j]) {
                    min = D[j];
                    minVertex = j;
                }
            }
            if(minVertex < 0) {
                continue;
            }
            // 방문
            visited[minVertex] = true;

            for(int j = 1; j <= N; j++) {
                if(!visited[j] && adjMatrix[minVertex][j] != Integer.MAX_VALUE && D[j] > D[minVertex] + adjMatrix[minVertex][j])  {
                    D[j] = D[minVertex] + adjMatrix[minVertex][j];
                }
            }
        }
        System.out.println(D[end]);
    }
}
