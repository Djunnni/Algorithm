import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * author djunnni
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                       

        StringBuilder out = new StringBuilder();
        // 테스트케이스 수
        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++) {
            out.append("#").append(testCase).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int answer = 0;

            int dx = abs(x1 - x2);
            int dy = abs(y1 - y2);

            int min = Math.min(dx, dy);
            int max = Math.max(dx, dy);

            answer = (min * 2) + ((max - min) % 2 == 0 ? (max - min) * 2 : (max - min) * 2 - 1);

            out.append(answer).append("\n");
        }

        System.out.println(out);
        br.close();
    }
    public static int abs(int a) {
        return (a < 0) ? -a : a;
    }
}
