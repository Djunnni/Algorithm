package solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 4195
 * @author djunnni
 * 
 * 풀이방식
 * F의 개수만큼 참여자가 늘어날 수 있어 2 * F의 공간을 할당한다.
 * String으로 이름을 나열하다보니 Map을 통해 고유 index 값을 하나씩 준다.
 * 해당 index로 union-find 를 통해 연결된 고리의 개수를 센다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine()); // 테스트케이스 개수

        for(int tc = 1; tc <= TC; tc++) { // 테스트 케이스 만큼 Loop

            Map<String, Integer> frendNumberMap = new HashMap<>();
            int F = Integer.parseInt(br.readLine()); // 친구 관계 수 [10만 이하]
            int[] list = new int[2 * F];
            int[] cnt = new int[2 * F];

            for(int i = 0; i < list.length; i++) {
                list[i] = i;
                cnt[i] = 1;
            }

            int numberPrefix = 0;


            for(int f = 1; f <= F; f++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String frend1 = st.nextToken();
                String frend2 = st.nextToken();

                if(!frendNumberMap.containsKey(frend1)) { // 친구 1번 넘버 배정
                    frendNumberMap.put(frend1, numberPrefix++);
                }
                if(!frendNumberMap.containsKey(frend2)) { // 친구 2번 넘버 배정
                    frendNumberMap.put(frend2, numberPrefix++);
                }

                int v = union(list, cnt, frendNumberMap.get(frend1), frendNumberMap.get(frend2));
                System.out.println(v);
            }
        }
    }
    public static int find(int[] lists, int a) {
        if(a == lists[a]) {
            return a;
        }

        return lists[a] = find(lists, lists[a]);
    }
    public static int union(int[] lists, int[] cnts, int a, int b) {
        a = find(lists, a);
        b = find(lists, b);

        if(a != b) {
            lists[b] = a;
            cnts[a] += cnts[b];
            cnts[b] = 1;
        }
        return cnts[a];
    }
}
