import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D4_7465_창용마을무리의개수
 * union-find를 할 줄 아는지 물어보는 문제.
 * author djunnni
 */
public class Solution {
    public static int[] relation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            // 마을에 사는 사람 수
            int N = Integer.parseInt(st.nextToken());

            // 사람 관계 수
            int M = Integer.parseInt(st.nextToken());

            make(N);

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                union(from, to);
            }

            int cnt = 0;
            for(int i = 1; i <= N; i++) {
                if(i == relation[i]) {
                    cnt++;
                }
            }
            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }
    public static int find(int a) {
        if(relation[a] == a) {
            return a;
        }
        return find(relation[a]);
    }
    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        // 항상 a가 작다고 하자.
        if(aRoot > bRoot) {
            int temp = aRoot;
            aRoot = bRoot;
            bRoot = temp;
        }
        if(aRoot == bRoot) {
            return false;
        }
        relation[bRoot] = aRoot;
        return true;
    }
    public static void make(int size) {
        relation = new int[size + 1];

        for(int i = 1; i <= size; i++) {
            relation[i] = i;
        }
    }
}
