import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Solved.ac 1717
 * 집합의 표현
 * @author djunnni
 */
public class Main {
    public static int[] set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // N + 1 개의 집합
        int M = Integer.parseInt(st.nextToken()); // M은 입력으로 주어진 연산의 개수

        makeSet(N);

        for(int i = 0; i < M; i++) { // 연산의 개수
            st = new StringTokenizer(br.readLine(), " ");
            int method = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            command(method, a, b);
        }
    }
    public static void command(int method, int a, int b) {
        switch(method) {
            case 0:
                union(a, b);
                break;
            case 1:
                getResult(a, b);
                break;
        }
    }
    public static void getResult(int a, int b) { // 결과 출력하기
        System.out.println(find(a) == find(b) ? "YES" : "NO");
    }
    public static int find(int a) {
        if(a == set[a]) {
            return a;
        } else {
            return set[a] = find(set[a]);
        }
    }
    public static void union(int a, int b) {
        a = find(set[a]);
        b = find(set[b]);

        if(a >= b) {
            set[a] = b;
        } else {
            set[b] = a;
        }
    }
    public static void makeSet(int N) { // 자기자신의 Set을 연결해둡니다.
        set = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            set[i] = i;
        }
    }
}
