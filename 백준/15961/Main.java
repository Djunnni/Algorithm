import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_15961_회전초밥
 * author djunnni
 * 1sec, 512MB
 *
 * 슬라이딩 기법을 활용하되, 구해야할 해가 무엇인지 파악해 풀자.
 */
public class Main {
    /**
     * 손님이 먹을 수 있는 초밥 가짓수의 최대값을 구하시오.
     */
    static int N, d, k, c, max = Integer.MIN_VALUE;
    static ArrayList<Short> sushis;
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

        sushis = new ArrayList();
        st = new StringTokenizer(br.readLine(), " ");
        // 회전 초밥 벨트에 놓인 접시의 수 (3_000_000)
        N = Integer.parseInt(st.nextToken());
        // 초밥의 가지수 d (3_000)
        d = Integer.parseInt(st.nextToken());
        // 연속해서 먹은 접시의 수 (3_000)
        k = Integer.parseInt(st.nextToken());
        // 쿠폰 번호 c (d 이하)
        c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            sushis.add(Short.parseShort(br.readLine()));
        }
        // 스시 리스트 출력
        // System.out.println(sushis);

        // k개의 접시를 먹을 때, 가능한 조합의 수를 계산하기
        findList();

        System.out.println(max);
    }

    private static void findList() {
        int count = 0;
        int cur = 0; // 기준점
        Map<Short, Integer> canSet = new HashMap(); // 초밥의 가짓수는 3000개가 있음.
        do {
            if (cur == 0) { // 초기 상태라면!
                for (int i = cur; i < cur + k; i++) {
                    short sushi = getSushi(i);
                    canSet.put(sushi, canSet.getOrDefault(sushi, 0) + 1);
                }
                count = canSet.size();
            } else { // 1단계 다음이라면!
                // 이전꺼 지우기
                short beforeSushi = getSushi(cur - 1);
                int before = canSet.getOrDefault(beforeSushi, 0) - 1;
                if (before == 0) {
                    canSet.remove(beforeSushi);
                } else {
                    canSet.put(beforeSushi, before);
                }
                // 새로운거 얻기
                short nextSushi = getSushi(cur + k - 1);
                canSet.put(nextSushi, canSet.getOrDefault(nextSushi, 0) + 1);
            }

            canSet.put((short) c, canSet.getOrDefault((short) c, 0) + 1);
            if (cur != 0) {
                if (count <= canSet.size()) {
                    count = canSet.size();
                } else if (count > canSet.size()) {
                    cur++;
                    continue;
                }
            }
            cur++;
        } while (cur < sushis.size());

        max = Math.max(count, max);
    }

    private static short getSushi(int index) {
        if (index < 0) {
            return (short) sushis.get(sushis.size() - Math.abs(index) % sushis.size());
        }
        return (short) sushis.get(index % sushis.size());
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
