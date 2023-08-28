import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author djunnni
 * 여행가자
 * 
 * 결국엔 선분간의 관계가 중요한데 서로 연결되어있는지 보장되면 된다.
 * 따라서 union-find로 해결하기
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 도시의 수
        int M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시 수
        StringTokenizer st;

        boolean matrix[][] = new boolean[N + 1][N + 1];

        // 도시별 연결관계
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken()) > 0;
            }
        }

        // 도시 여행 계획
        st = new StringTokenizer(br.readLine());
        Queue<Integer> visits = new LinkedList<>();

        while(st.hasMoreTokens()) {
            visits.add(Integer.parseInt(st.nextToken()));
        }

        int[] relation = new int[N + 1]; // 자기 자신으로 연결고리 잡기
        for(int i = 1; i <= N; i++) {
            relation[i] = i;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;
                if(matrix[i][j]) {
                    union(relation, i, j);
                }
            }
        }

        String answer = "YES";
        int firstParent = getParent(relation, visits.peek());
        while(!visits.isEmpty()) {
            int p = visits.poll();
            if(firstParent != getParent(relation, p)) {
                answer = "NO";
                break;
            }
        }
        System.out.println(answer);

    }
    public static int getParent(int[] relation, int a) {
        if(relation[a] == a) return a;
        return relation[a] = getParent(relation, relation[a]);
    }
    public static void union(int[] relation, int a, int b) {
        a = getParent(relation, a);
        b = getParent(relation, b);

        if(a < b) {
            relation[b] = a;
        } else {
            relation[a] = b;
        }
    }
}
