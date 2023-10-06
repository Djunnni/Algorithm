import java.io.*;

/**
 * 게임을 만든 동준이
 * BJ_2847
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 레벨을 난이도 순으로 배치했다.
        // 쉬운레벨이 어려운 레벨보다 점수를 많이 받는 경우다.
        // 문제를 해결하고자 특정 레벨의 점수를 감소시키려고 한다.
        // 각 레벨을 클리어 할 때, 주는 점수가 증가하게 만드려고 한다.
        int stages[] = new int[N];
        for(int i = 0; i < N; i++) {
            int level = Integer.parseInt(br.readLine());
            stages[i] = level;
        }

        // 각 스테이지마다 레벨은 순서대로 증가해야 된다. 난 점수를 받았다.
        int answer = 0;
        for(int i = N - 1; i >= 1; i--) {
            // 내가 더 크다면 내 앞은 나보다 작아야 한다.
            if(stages[i - 1] >= stages[i]) {
                int gap = stages[i - 1] - stages[i];
                stages[i - 1] -= (gap + 1);
                answer += (gap + 1);
            }
        }

        System.out.println(answer);
    }

}
