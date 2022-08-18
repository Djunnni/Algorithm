import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SW_1247_최적_경로
 *
 * 가지치기 전략 적용 -> 최소값으로 정한 것보다 클 경우에는 더이상 탐색을 하지 않습니다.
 * 실행시간이 1/3 정도로 감소
 * 
 * author djunnni
 */
public class Solution2 {
    public static int answer, N;
    public static Point home, company;
    public static Point list[];
    public static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());


        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            answer = Integer.MAX_VALUE;
            list = new Point[N];
            visited = new boolean[N];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            company = new Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
            home = new Point(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            for(int i = 0; i < N; i++) {
                list[i] = new Point(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                );
            }

            findMinimumRoute(0, 0, company.x, company.y);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
    public static void findMinimumRoute(int cnt, int distance, int beforeX, int beforeY) {
        if(distance > answer) {
            return;
        }
      if(cnt == N) {
            answer = Math.min(answer, distance + Math.abs(beforeX - home.x) + Math.abs(beforeY - home.y));
            return;
        }
        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            findMinimumRoute(
                    cnt + 1,
                    distance + Math.abs(beforeX - list[i].x) + Math.abs(beforeY - list[i].y),
                    list[i].x,
                    list[i].y
                    );
            visited[i] = false;
        }
    }
}
