import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author djunnni
 * BJ_13549(숨바꼭질 3)
 * 매우 최적화한 경우
 *
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        int MAX = 100_001;
        // LEFT or RIGHT?
        boolean visited[] = new boolean[MAX];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { N, 0 });
        int cost = 0;

        while(!queue.isEmpty()) {
            int data[] = queue.poll();
            visited[data[0]] = true;

            if(data[0] == K) {
                cost = data[1];
                break;
            }

            if(data[0] * 2 < MAX && !visited[data[0] * 2])
                queue.add(new int[] { data[0] * 2, data[1] });
            if(data[0] >= 1 && !visited[data[0] - 1])
                queue.add(new int[] { data[0] - 1, data[1] + 1 });
            if(data[0] + 1 < MAX && !visited[data[0] + 1])
                queue.add(new int[] { data[0] + 1, data[1] + 1 });
        }

        System.out.println(cost);
    }
}
