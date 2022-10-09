import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * acmicpc.BJ_16637_괄호 추가하기
 * author djunnni
 */
public class Solution {
    static int answer, N;
    static ArrayList<Integer> num = new ArrayList<>();
    static ArrayList<Character> op = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수식의 길이
        N = Integer.parseInt(br.readLine());

        String data = br.readLine();

        for (int i = 0; i < data.length(); i++) {
            if (i % 2 == 0) {
                num.add(data.charAt(i) - '0');
            } else {
                op.add(data.charAt(i));
            }
        }
        // answer를 최소로 초기화
        answer = Integer.MIN_VALUE;

        int start = num.get(0);
        dfs(0, start);

        // 출력
        System.out.println(answer);
    }

    private static void dfs(int cnt, int sum) {
        if (cnt >= op.size()) {
            answer = Integer.max(answer, sum);
            return;
        }

        // 그냥 더하기
        int one = cal(cnt, sum, num.get(cnt + 1));
        dfs(cnt + 1, one);

        if (cnt + 1 < op.size()) {
            int two = cal(cnt + 1, num.get(cnt + 1), num.get(cnt + 2));
            int result = cal(cnt, sum, two);
            dfs(cnt + 2, result);
        }
    }

    public static int cal(int opIdx, int a, int b) {
        switch (op.get(opIdx)) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 1;
    }
}
