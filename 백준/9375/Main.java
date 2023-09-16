import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author djunnni
 * 패션왕 신해빈
 */
public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static int TC, factorialTable[];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        TC = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for(int tc = 1; tc <= TC; tc++) {
            test();
        }
        System.out.println(sb.toString());
    }
    public static void answer(Map<String, List<String>> map) {
        int ans = 1;
        // 한번 입었던 옷들의 조합은 다신 입지 않는다. 오늘날 안경, 코트, 상의, 신발을 입었다면 바지를 추가로 입거나 기존게 빠져야 한다.
        // 해빈이가 가진 의상들이 주어졌을 때, 해빈이는 알몸이 아닌 상태로 며칭동안 밖에 돌아다닐 수 있을까?

        // 각 의상별 조합의 개수에서 다른 케이스가 0이 발생하는 개수를 모두 빼준다.
        // gear 2개 eyewear 1개 => 00 01 10 11 | 0 1 => 000(x) 001 010 011 100 101 111
        // gear 2개, eyewear 1개, hat 1개 => 00 01 10 11 | 0 1 | 0 1 => 0000(x) 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1110 1111
        // 4 * 2 * 2 => 16 - 2 = > 14
        int keyLength = map.size();

        int[] lens = new int[keyLength];
        int spot = 0;
        for(String key : map.keySet()) {
            List<String> items = map.get(key);
            // items 에서 0개를 고르거나 1개를 고른 횟수
            lens[spot++] = items.size() + 1;
        }
        for(int size : lens) {
            ans *= size;
        }
        ans -= 1;

        sb.append(ans).append("\n");
    }
    public static void test() throws IOException {
        int N = Integer.parseInt(br.readLine()); // 의상 수 30개 이하
        Map<String, List<String>> map = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String item = st.nextToken();
            String category = st.nextToken();

            List<String> items = map.getOrDefault(category, null);
            if (items == null) {
                List<String> temp = new ArrayList<>();
                temp.add(item);
                map.put(category, temp);
            } else {
                items.add(item);
            }
        }
        answer(map);
    }
}
