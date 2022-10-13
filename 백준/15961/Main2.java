import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_15961_회전초밥
 * author djunnni
 * 1sec, 512MB
 *
 * 슬라이딩 기법을 활용하되, int 배열을 활용해 몇개를 카운트 하는지 체크
 * 168492kb, 560ms
 */
public class Main {
    /**
     * 손님이 먹을 수 있는 초밥 가짓수의 최대값을 구하시오.
     */
    static int N, d, k, c, max = Integer.MIN_VALUE;
    static int[] sushis, visited;
    static String data = "8 30 4 30\n" +
            "7\n" +
            "9\n" +
            "7\n" +
            "30\n" +
            "2\n" +
            "7\n" +
            "9\n" +
            "25";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedReader(new StringReader(data));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        // 회전 초밥 벨트에 놓인 접시의 수 (3_000_000)
        N = Integer.parseInt(st.nextToken());
        // 초밥의 가지수 d (3_000)
        d = Integer.parseInt(st.nextToken());
        // 연속해서 먹은 접시의 수 (3_000)
        k = Integer.parseInt(st.nextToken());
        // 쿠폰 번호 c (d 이하)
        c = Integer.parseInt(st.nextToken());

        sushis = new int[N];
        visited = new int[d + 1];
        for (int i = 0; i < N; i++) {
            sushis[i] = Integer.parseInt(br.readLine());
        }
        // 스시 리스트 출력
        // System.out.println(sushis);

        // k개의 접시를 먹을 때, 가능한 조합의 수를 계산하기
        findList();

        System.out.println(max);
    }

    private static void findList() {
        int total = 0;
        for (int i = 0; i < k; i++) {
            if (visited[getSushi(i)] == 0)
                total++;
            visited[getSushi(i)]++;
        }
        max = total;

        for (int i = 1; i < N; i++) {
            if (max <= total) {
                if (visited[c] == 0) {
                    max = total + 1;
                } else {
                    max = total;
                }
            }

            visited[getSushi(i - 1)]--;
            if (visited[getSushi(i - 1)] == 0)
                total--;

            if (visited[getSushi(i + k - 1)] == 0)
                total++;
            visited[getSushi(i + k - 1)]++;
        }
    }

    private static int getSushi(int index) {
        if (index < 0) {
            return sushis[sushis.length - Math.abs(index) % sushis.length];
        }
        return sushis[index % sushis.length];
    }
}
/**
 * 7 9 7 30 2 7 9 25
 * 7 9 7 (x)
 * 9 7 30 2 (o)
 * 7 30 2 7 (x)
 * 30 2 7 9 (o)
 * 2 7 9 25 (o)
 * 7 9 25 7 (x)
 * 슬라이딩으로 가운데를 그대로 유지한 체, 앞과 뒤만 바꿔간다면?
 * 1 2 3 4 1 2 3 4
 * 
 */
