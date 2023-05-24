import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 지름길의 개수 [12이하]
        int D = Integer.parseInt(st.nextToken()); // 고속도로의 길이 [10_000 이하]

        int[] answer = new int[D + 1]; // 정답거리
        Arrays.fill(answer, Integer.MAX_VALUE);

        Map<Integer, List<int[]>> map = new HashMap<>();

        for(int route = 1; route <= N; route++) { // 지름길의 개수
            st = new StringTokenizer(sc.nextLine(), " ");
            int startPoint = Integer.parseInt(st.nextToken());
            int endPoint = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(endPoint > D) continue; // END 포인트가 D 보다 더 멀다면 skip 한다.
            if(endPoint - startPoint < cost) continue; // 끝지점에서 시작지점을 뺀 거리보다 더 비싸면 skip 한다.

            map.putIfAbsent(endPoint, new ArrayList<>()); // 비어있을 때, 빈 배열 넣기
            map.get(endPoint).add(new int[] { startPoint, cost });
        }

        answer[0] = 0;

        for(int i = 1; i <= D; i++) {
            List<int[]> list = map.getOrDefault(i, new ArrayList<>());
            if(!list.isEmpty()) {
                for(int[] data : list) {
                    if(answer[data[0]] + data[1] > answer[i]) continue;
                    answer[i] = Math.min(answer[i - 1] + 1, answer[data[0]] + data[1]);
                }
                continue;
            }
            answer[i] = answer[i - 1] + 1;
        }

        System.out.println(answer[D]); // 정답 출력
    }
}
