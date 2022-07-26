import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N개의 주차공간, M개의 차량
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] Rs = new int[N];
        int[] Wk = new int[M];
        int[] moves = new int[2*M];
        for(int i = 0; i < N; i++) {
            Rs[i] = sc.nextInt();
        }
        for(int j = 0; j < M; j++) {
            Wk[j] = sc.nextInt();
        }
        // 주차장 출입순서, 양 => 차량 i가 주차장에 입장, 음 => 차량 i가 주차장에서 나감
        Queue<Integer> waitingZone = new LinkedList<>();
        for(int i = 0; i < 2 * M; i++) {
            moves[i] = sc.nextInt();
        }

        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // KEY는 차량
        // value는 주차장
        for(int i = 0; i < M; i++) {
            map.put(i, 0);
        }

        int visited[] = new int[N];
        for(int i = 0; i < 2 * M; i++) {
            boolean willOut = moves[i] < 0;
            int action = Math.abs(moves[i]);

            if(!willOut) {
                // 들어가는 차라면
                int zone = -1;
                for(int v = 0; v < N; v++) {
                    if(visited[v] == 0) {
                        zone = v;
                        break;
                    }
                }
                if(zone == -1) {
                    waitingZone.add(action);
                } else {
                    visited[zone] = action;
                    map.put(action, zone);
                }
            } else {
                // 나가는 차라면
                int where = map.get(action);
                visited[where] = 0;
                answer += Wk[action - 1] * Rs[where];
                if(!waitingZone.isEmpty()) {
                    int car = waitingZone.poll();
                    visited[where] = car;
                    map.put(car, where);
                }
            }

        }


        System.out.println(answer);
    }
}
