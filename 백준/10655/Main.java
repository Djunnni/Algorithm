import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 마라톤 1
 * @author djunnni
 */
public class Main {
    public static class CheckPoint {
        int x, y;
        public CheckPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 10만개

        CheckPoint[] points = new CheckPoint[N + 1];
        int[] distances = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i] = new CheckPoint(x, y);
        }

        for(int i = 2; i <= N; i++) { // 사이에 있는 지점까지의 거리
            distances[i] = distances[i - 1] + getDistance(points[i], points[i - 1]); // i 번째는 i, i-1번째의 거리가 들어간다.
        }

        // 한 지점을 뺄 수 있다는건 전체길이에서 해당 구간에 값만 뺐을 때 최소를 구하면 됨
        int minValue = Integer.MAX_VALUE;
        // System.out.println(Arrays.toString(distances));

        for(int i = 2; i < N; i++) {
            // 2번째를 뺀다는건 -> N번째에서 3번째를 뺀 거리와 1번째까지의 거리의 합에 3번째와 1번째의 거리 합
            int d = (distances[N] - distances[i + 1]) + distances[i - 1] + getDistance(points[i + 1], points[i - 1]);
            minValue = Math.min(minValue, d);
        }

        System.out.println(minValue);
    }
    public static int getDistance(CheckPoint a, CheckPoint b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
