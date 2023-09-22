import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 톱니바퀴
 * @author djunnni
 * 어느 범위까지 수정할 수 있는 지 선 탐색 후 조치가 되어야함
 * 반대로 Main2번은 탐색과정에서 수정을 진행하면서 넘어가는 경우로 문제의도와 맞지 않음
 */
public class Main {
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
    // 시계
    public static void turnRight(int which, boolean[] changes, boolean[] visited) {
        visited[which] = true;
        heads[which] = (heads[which] - 1 + LENGTH) % LENGTH; // 이동!

        if(which > 1 && changes[which - 1] && !visited[which - 1]) {
            turnLeft(which - 1, changes, visited);
        }
        if(which < N - 1 && changes[which + 1] && !visited[which + 1]) {
            turnLeft(which + 1, changes, visited);
        }
    }
    // 반시계
    public static void turnLeft(int which, boolean[] changes, boolean[] visited) {
        visited[which] = true;
        heads[which] = (heads[which] + 1 + LENGTH) % LENGTH; // 이동!

        if(which > 1 && changes[which - 1] && !visited[which - 1]) {
            turnRight(which - 1, changes, visited);
        }
        if(which < N - 1 && changes[which + 1] && !visited[which + 1]) {
            turnRight(which + 1, changes, visited);
        }
    }
    public static void checkRange(boolean[] changes, int number) {
        changes[number] = true; // 현재지점은 변환범위

        char leftRight = getRightSignal(number - 1);
        char currentLeft = getLeftSignal(number);
        char currentRight = getRightSignal(number);
        char rightLeft = getLeftSignal(number + 1);

        if(leftRight != ' ' && !changes[number - 1] && leftRight != currentLeft) {
            checkRange(changes, number - 1);
        }
        if(rightLeft != ' ' && !changes[number + 1] && rightLeft != currentRight) {
            checkRange(changes, number + 1);
        }
    }
    public static void play() throws IOException {
        for(int i = 1; i <= K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            boolean isClockDirection = Integer.parseInt(st.nextToken()) == 1;
            boolean[] canChanges = new boolean[N];
            // 어디까지가 변환범위인지 찾기
            checkRange(canChanges, number);
            if(isClockDirection) {
                turnRight(number, canChanges, new boolean[N]);
            } else {
                turnLeft(number, canChanges, new boolean[N]);
            }
//            for(String s : list) {
//                System.out.println(s);
//            }
//            System.out.println(Arrays.toString(heads));
//            System.out.println(Arrays.toString(canChanges));
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
