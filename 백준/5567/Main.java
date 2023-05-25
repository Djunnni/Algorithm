import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 결혼식 백준_5567
 * 
 * 풀이방법
 * 양방향 연결로 제작
 * 1번의 친구 그리고 그 친구의 친구들까지만 초대가 가능해
 * turn을 주고 2턴까지만 연산을 진행
 * 그리고 visited를 통해서 중복체크를 검사하고 큐에 새롭게 친구를 넣는다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 상근이의 동기 수
        int M = Integer.parseInt(br.readLine()); // 리스트의 길이 M

        boolean visited[] = new boolean[N + 1];
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!map.containsKey(a)) map.putIfAbsent(a, new ArrayList<>());
            if(!map.containsKey(b)) map.putIfAbsent(b, new ArrayList<>());

            // 양방향으로 연결
            map.get(a).add(b);
            map.get(b).add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int friend : map.getOrDefault(1, new ArrayList<>())) {
            queue.add(friend);
        }

        int turn = 0;
        while(true) {
            turn++;
            int size = queue.size();
            while(size-- > 0) {
                int friend = queue.poll();
                if(!visited[friend]) {
                    for(int f : map.get(friend)) {
                        queue.add(f);
                    }
                }
                visited[friend] = true;
            }
            if(turn == 2) {
                break;
            }
        }

        int answer = 0;
        for(int i = 2; i <= N; i++) {
            if(visited[i]) answer++;
        }
        System.out.println(answer);
    }

}
