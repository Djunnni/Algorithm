import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author djunnni
 * BJ_13549(숨바꼭질 3)
 *
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        int MAX = 200_001;
        // LEFT or RIGHT?
        int distance[] = new int[MAX];
        Arrays.fill(distance, Integer.MAX_VALUE);

        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        queue.add(new int[] { N, 0 });
        distance[N] = 0;

        while(!queue.isEmpty()) {
            int data[] = queue.poll();
            if(data[0] >= MAX || data[0] < 0) continue;
            if(data[1] > distance[data[0]]) continue;
            distance[data[0]] = data[1];

            if(data[0] == K) continue;

            if(data[0] * 2 < MAX && distance[data[0] * 2] > data[1])
                queue.add(new int[] { data[0] * 2, data[1] });
            if(data[0] + 1 < MAX && distance[data[0] + 1] > data[1] + 1)
                queue.add(new int[] { data[0] + 1, data[1] + 1 });
            if(data[0] >= 1 && distance[data[0] - 1] > data[1] + 1)
                queue.add(new int[] { data[0] - 1, data[1] + 1 });
        }

        System.out.println(distance[K]);
    }
}
