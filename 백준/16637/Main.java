package acmicpc.BJ_16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * acmicpc.BJ_16637_괄호 추가하기
 * author djunnni
 * 시작 19:20 - 21:00
 *
 * 시간이 많이 걸린 이유
 * calculate에서 연산 방법에 대해 1시간 10분정도 잡아먹음.
 */
public class Main {
    static int answer, N, operatorCount;
    static String data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수식의 길이
        N = Integer.parseInt(br.readLine());

        data = br.readLine();

        // answer를 최소로 초기화
        answer = Integer.MIN_VALUE;

        /**
         * 이 문제는 괄호를 적절히 추가해 만들 수 있는 식의 결과 최대값을 구하는 문제다.
         * 수식을 계산할 때는 왼쪽에서부터 순서대로다.
         *
         * 전적절한 최대값을 구하는 문제로 보인다.
         * 괄호안에는 연산자는 1개만 있어야한다.
         * 괄호가 0개일 때, 괄호가 1개 있을 때, 괄호가 2개 있을 때, 괄호가 ... n개 있을 때,
         */

        operatorCount = N / 2; // 연산자의 개수

        // 연산자가 없다는건 자체로 숫자 1개라는 의미
        if (operatorCount == 0) {
            answer = Integer.parseInt(data);
        } else {
            boolean visited[] = new boolean[operatorCount];
            permutation(visited, 0);
        }

        // 출력
        System.out.println(answer);
    }

    private static void permutation(boolean[] visited, int count) {
        if (count == operatorCount) {
            calculate(visited);
            return;
        }

        if ((count == 0) || (count > 0 && !visited[count - 1])) {
            visited[count] = true;
            permutation(visited, count + 1);
        }
        visited[count] = false;
        permutation(visited, count + 1);
    }

    private static void calculate(boolean[] visited) {
        Queue<String> queue = new LinkedList<>();

        for (int i = 0; i < visited.length; i++) {
            int _sum = 0;
            int left = data.charAt(i * 2) - '0';
            char operator = data.charAt(i * 2 + 1);
            int right = data.charAt((i + 1) * 2) - '0';

            if (visited[i]) {
                _sum = operate(left, right, operator);
                queue.add(_sum + "");
            } else {
                if (i == 0 || (i > 0 && visited[i - 1] == visited[i]))
                    queue.add(left + "");
                queue.add(operator + "");
            }

            if (i == visited.length - 1 && visited[i] == false) {
                queue.add(right + "");
            }

        }

        int sum = Integer.parseInt(queue.poll());
        int queueSize = queue.size() / 2; // operator의 개수
        while (queueSize-- > 0) {
            char operator = queue.poll().charAt(0);
            int right = Integer.parseInt(queue.poll());
            sum = operate(sum, right, operator);
        }

        answer = Math.max(sum, answer);
    }

    private static int operate(int sum, int right, char operator) {
        int v = 0;
        switch (operator) {
            case '+':
                v = sum + right;
                break;
            case '-':
                v = sum - right;
                break;
            case '*':
                v = sum * right;
                break;
        }
        return v;
    }
}
/**
 * ex) 8*3+5
 *
 * 8 * 3 + 5 => 29
 * (8 * 3) + 5 => 29
 * 8 * (3 + 5) => 64
 * 0 0
 * 1 0
 * 0 1
 *
 * ex) 8*3+5+2
 *
 * (8*3)+5+2 => 31
 * 8*(3+5)+2 => 66
 * 8*3+(5+2) => 31
 * 0 0 0
 * 1 0 0
 * 0 1 0
 * 0 0 1
 * 1 0 1
 *
 * ex) 3+8*7-9*2
 * (3+8)*7-9*2 => 136
 * 3+(8*7)-9*2 => 100
 * 3+8*(7-9)*2 => -44
 * 3+8*7-(9*2) => 59
 * (3+8)*(7-9)*2 => -44
 * (3+8)*7-(9*2) => 59
 * 3+(8*7)-(9*2) => 59 - 18 = 41
 * 0 0 0 0
 * 1 0 0 0
 * 0 1 0 0
 * 0 0 1 0
 * 0 0 0 1
 * 1 0 1 0
 * 1 0 0 1
 * 0 1 0 1
 *
 * 연산자 1개에 2개의 값이 필요하다.
 * 괄호는 1개당 2개의 숫자를 먹으니 총 3개의 글자를 가져감. 그 사이에 1개의 문자가 더 필요하므로 4개
 * 9글자면 9 / 4 => 2
 * 7글자면 7 / 4 => 1
 * 5글자면 5 / 4 => 1
 * 3글자면 3 / 4 => 0
 */
