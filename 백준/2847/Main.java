import java.io.*;

/**
 * 게임을 만든 동준이
 * BJ_2847
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

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
