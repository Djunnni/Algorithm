package solved._14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 스타트와 링크
 * @author djunnni
 */
public class Main {
    public static BufferedReader br;
    public static int N, ANSWER, map[][];
    public static void main(String[] args) throws IOException {
        // 초기화
        initialize();
        // 풀기
        solve();
        // 출력
        System.out.println(ANSWER);
    }
    public static int calculateScore(List<Integer> team) {
        int score = 0;
        for(int i = 0; i < N / 2; i++) {
            for(int j = i + 1; j < N / 2; j++) {
                score += map[team.get(i)][team.get(j)] + map[team.get(j)][team.get(i)];
            }
        }
        return score;
    }
    public static void calculate(boolean[] teams) {
        List<Integer> blue = new ArrayList<>();
        List<Integer> red = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(teams[i]) blue.add(i);
            else red.add(i);
        }
        ANSWER = Math.min(ANSWER, Math.abs(calculateScore(blue) - calculateScore(red)));
    }
    // N명이라면 나는 N/2명을 골라야하며 이떄의 시너지 값이 최소여야 한다. (일단 N명 뽑기)
    public static void combination(boolean[] teams, int start, int end, int select) {
        if(select == teams.length / 2) { // 반만큼 선택했다면
            calculate(teams);
            return;
        }

        for(int i = start; i <= end; i++) {
            if(teams[i]) continue;
            teams[i] = true;
            combination(teams, i + 1, end, select + 1);
            teams[i] = false;
        }
    }
    public static void solve() {
        boolean teams[] = new boolean[N + 1]; // true -> 스타크, false -> 링크

        combination(teams, 1, N, 0);
    }
    public static void initialize() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 사람 수
        ANSWER = Integer.MAX_VALUE;

        map = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
