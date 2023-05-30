import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최소스패닝트리의 거리구하는 문제로
 * 사이클 발생여부를 체크해 최소비용을 구하는 문제
 * while문으로 반복한다는 점 참고
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int M = Integer.parseInt(st.nextToken()); // 집의 수
            int N = Integer.parseInt(st.nextToken()); // 길의 수
            if(M == 0 && N == 0) break;

            int[] house = new int[M + 1];
            for (int i = 0; i <= M; i++) {
                house[i] = i;
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                return Integer.compare(a[2], b[2]);
            });

            int totalCost = 0;
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int X = Integer.parseInt(st.nextToken()); // 출발
                int Y = Integer.parseInt(st.nextToken()); // 도착
                int Z = Integer.parseInt(st.nextToken()); // 비용

                pq.add(new int[]{X, Y, Z});
                totalCost += Z;
            }

            int answer = 0;

            while (!pq.isEmpty()) {
                int[] edge = pq.poll();
                if (find(house, edge[0]) != find(house, edge[1])) {
                    union(house, edge[0], edge[1]);
                    answer += edge[2];
                }
            }
            System.out.println(totalCost - answer);
        }
    }

    private static void union(int[] house, int i, int i1) {
        i = find(house, i);
        i1 = find(house, i1);

        if(i < i1) {
            house[i1] = i;
        } else {
            house[i] = i1;
        }
    }

    private static int find(int[] house, int i) {
        if(house[i] == i) {
            return i;
        }

        return house[i] = find(house, house[i]);
    }

}