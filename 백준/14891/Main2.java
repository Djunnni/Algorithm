package solved._14891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 톱니바퀴
 * @author djunnni
 * 오답 케이스
 */
public class Main2 {
    static int N = 5, LENGTH= 8, K;
    static String list[];
    static int heads[];
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        initalize();
        play();
        answer();
    }
    public static void answer() {
        int answer = 0;
        for(int i = 1; i < N; i++) {
            char direction = list[i].charAt(heads[i]);
            if(direction == '1') {
                answer += Math.pow(2, i - 1);
            }
        }
        System.out.println(answer);
    }
    public static char getRightSignal(int which) {
        if(which < 1 || which > 4) return ' ';
        int right = (heads[which] + 2 + 8) % 8;
        return list[which].charAt(right);
    }
    public static char getLeftSignal(int which) {
        if(which < 1 || which > 4) return ' ';
        int left = (heads[which] - 2 + 8) % 8;
        return list[which].charAt(left);
    }
    public static void turnRight(int which, boolean[] visited) {
        // System.out.print("[right]"+which + " >> ");
        char leftRight = getRightSignal(which - 1);
        char currentLeft = getLeftSignal(which);
        char currentRight = getRightSignal(which);
        char rightLeft = getLeftSignal(which + 1);
        visited[which] = true;
        heads[which] = (heads[which] + 1 + LENGTH) % LENGTH; // 이동!

        // System.out.println("{"+leftRight + " ," + currentLeft + "}" + "["+rightLeft + " ," + currentRight + "]");
        if(leftRight != ' ' && !visited[which - 1] && leftRight != currentLeft) { // 현재 돌아갈 애의 왼쪽이 왼쪽의 오른방향과 같을 경우
            turnRight(which - 1, visited);
        }
        if(rightLeft != ' ' && !visited[which + 1] && rightLeft != currentRight) {
            turnLeft(which + 1, visited);
        }
    }
    public static void turnLeft(int which, boolean[] visited) {
        // System.out.print("[left]" + which + " >> ");
        // 톱니바퀴가 회전할 때, 서로 맞닿은 극에 따라서 옆에 있는 톱니바퀴를 회전할 수도 있고 안할 수도 있다.
        // 회전하기전, left가 다른지, 같은지
        char leftRight = getRightSignal(which - 1);
        char currentLeft = getLeftSignal(which);
        char currentRight = getRightSignal(which);
        char rightLeft = getLeftSignal(which + 1);
        visited[which] = true;
        heads[which] = (heads[which] - 1 + LENGTH) % LENGTH; // 이동!

        // System.out.println("{"+leftRight + " ," + currentLeft + "}" + "["+rightLeft + " ," + currentRight + "]");
        if(leftRight != ' ' && !visited[which - 1] && leftRight != currentLeft) { // 현재 돌아갈 애의 왼쪽이 왼쪽의 오른방향과 같을 경우
            turnLeft(which - 1, visited);
        }

        if(rightLeft != ' ' && !visited[which + 1] && rightLeft != currentRight) {
            turnRight(which + 1, visited);
        }
    }
    public static void play() throws IOException {
        for(int i = 1; i <= K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            boolean isClockDirection = Integer.parseInt(st.nextToken()) == 1;
            boolean[] visited = new boolean[N];
            if(isClockDirection) {
                turnRight(number, visited);
            } else {
                turnLeft(number, visited);
            }
        }
    }
    public static void initalize() throws IOException {
        list = new String[N];
        heads = new int[N]; // 12시 방향의 위치 값을 기록합니다.
        for(int i = 1; i < N; i++) {
            list[i] = br.readLine(); // 12시 방향부터 시계방향 순서대로 데이터가 들어옴 N : 0, S : 1
        }
        K = Integer.parseInt(br.readLine());
    }
}
