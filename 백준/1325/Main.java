import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solve.ac 1325
 * 이 회사는 N개의 컴퓨터로 이루어져 있다. 한 번의 해킹으로 여러 개의 컴퓨터를 해킹 할 수 있는 컴퓨터를 해킹하려 한다.
 * 컴퓨터들은 신뢰하는 관계와 신뢰하지 않는 관계로 이루어져 있는데 A가 B를 신뢰하는 경우에 B를 해킹하면 A도 해킹 가능하다.
 *
 * 신뢰관계가 주어졌을 떄, 한 번에 가장많은 해킹을 할 수 있는 컴퓨터 번호를 출력해라.
 *
 * ***************** 문제 진입 시, 내가 잘못 들어간 부분 *********************
 * top => bottom 방식으로 계산하는데 visited를 HashSet<>[]으로 static하게 위치들마다 관리하려고 했음. => 메모리 초과
 * list를 bfs, dfs로 단순 순회 => 시간 초과
 * 
 * 다음번에 비슷한 문제도 bottom => top으로도 접근해봐야겠음.
 *
 * author djunnni
 */
public class Main {
    static int N, M;
    static ArrayList<Integer>[] list;
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(rd.readLine(), " ");

        // N: 컴퓨터 수, M: 신뢰하는 관계
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        /**
         * 예시) list[i]를 해킹하면 리스트에 포함된 위치까지 해킹들이 가능하다.
         *             list[1] =
         *             list[2] =
         *             list[3] = 1, 2
         *             list[4] = 3
         *             list[5] = 3
         */
        for (int i = 1; i <= M; i++) {
            token = new StringTokenizer(rd.readLine(), " ");
            // from가 to를 신뢰한다. <=> to를 해킹하면 from도 해킹할 수 있다.
            int from = Integer.parseInt(token.nextToken());
            int to = Integer.parseInt(token.nextToken());
            list[from].add(to);
        }
        /**
         * 나올 수 있는 경우,
         * 1. 단방향 신뢰, 2. 단방향 신뢰 - 사이클, 3. 양방향
         */
        count = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            boolean visited[] = new boolean[N + 1];
            dfs(i, visited);
        }

        int MAX = count[1];
        for(int i = 2; i <= N; i++) {
            if(MAX < count[i]) {
                MAX = count[i];
            }
        }

        for(int i = 1; i <= N; i++) {
            if(MAX == count[i])
                System.out.print(i + " ");
        }
    }
    // bottom -> up
  public static void dfs(int spot, boolean[] visited) {
        visited[spot] = true;
        for(int n : list[spot]) {
            if(!visited[n]) {
                count[n]++;
                dfs(n, visited);
            }
        }
  }
}
