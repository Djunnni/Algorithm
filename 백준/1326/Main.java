import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac 1326
 * 폴짝폴짝
 *
 * 어떤 징검다리에서 점프할 때는 징검다리에 적힌 수의 배수만큼 떨어진 곳으로만 갈 수 있다.
 * 이 개구리는 a번째에서 b번째 징검다리로 가려한다. a에서 시작해 최소 몇번의 점프를 해 b로 갈 수 있는지 작성해라
 * author djunnni
 */
public class Main {
    static class Jump {
        // point: 다음 위치, cnt: point까지 오는 데 까지 걸린 시간
        int cnt, point;
        public Jump(int point, int cnt) {
            this.cnt = cnt;
            this.point = point;
        }
    }
    static int N, start, end;
    static int arr[];
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = null;

        // 징검다리 개수 N
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        token = new StringTokenizer(br.readLine(), " ");
        // 각 위치마다 해당하는 점프 가능 거리를 넣기
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(token.nextToken());
        }

        token = new StringTokenizer(br.readLine());
        // 시작점과 끝점(지문에서 나왔듯 start와 end의 크기는 자유롭다. 즉, 왼쪽 -> 오른쪽, 오른쪽 -> 왼쪽 모두 가능
        start = Integer.parseInt(token.nextToken());
        end = Integer.parseInt(token.nextToken());

        System.out.println(findRoot(start));
    }
    public static int findRoot(int start) {
        visited = new boolean[N + 1];
        visited[start] = true;

        Queue<Jump> queue = new LinkedList<>();
        queue.add(new Jump(start, 0));

        while(!queue.isEmpty()) {
            Jump where = queue.poll();

            if(where.point == end) {
                return where.cnt;
            }
            int i = 1;
            while(true) {
                int next =  where.point + (i * arr[where.point]);
                if(next > N) break;
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(new Jump(next, where.cnt + 1));
                }
                i++;
            }

            i = 1;
            while(true) {
                int next = where.point - (i * arr[where.point]);
                if(next < 1) break;
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(new Jump(next, where.cnt + 1));
                }
                i++;
            }

        }
        return -1;
    }
}
