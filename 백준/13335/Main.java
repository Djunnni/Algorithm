import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 다리를 건너는 트럭 수
        int W = sc.nextInt(); // 다리 길이
        int L = sc.nextInt(); // 다리의 최대 하중

        int bridges[] = new int[W];
        // 다리를 건너는 트럭의 무게들 저장
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            queue.add(sc.nextInt());
        }
        // 결과: 시간
        int time = 0;

        // 다리 무게(시작 0)
        int bridge_weight = 0;
        // queue가 비었다면 break;
        while(!queue.isEmpty()) {

            // 배열 첫번째에 도달했을 떄, 값 저장
            int reach = bridges[0];
            // 한칸씩 옮기기
            for(int i = 1; i < W; i++) {
                bridges[i - 1] = bridges[i];
            }
            // 옮기고나서 맨끝 0으로 변경
            bridges[W - 1] = 0;

            // 도달한 곳에 무게가 있을 때, 다리무게에서 빼기
            if(reach != 0) {
                bridge_weight -= reach;
            }
            // queue가 비어있지 않고 queue 첫번째랑 다리무게의 합이 총무게보다 작거나 같으면 하나를 끝에 넣는다.
            if(queue.peek() != null && bridge_weight + queue.peek() <= L) {
                bridges[W - 1] = queue.poll();
                bridge_weight += bridges[W - 1];
            }
            // 시간 1 증가
            time++;
        }

        // 시간 + 마지막에 W길이만큼 갔을 때,
        System.out.println(time + W);
    }
}
