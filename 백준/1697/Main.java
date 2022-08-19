package acmicpc.BJ_1697;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * BJ_1697_숨바꼭질
 * author djunnni
 */
public class Main {
    static class Action {
        int position;
        int time;

        public Action(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }
    static int N, K, answer;
    static boolean isVisited[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        isVisited = new boolean[100001];
        answer = Integer.MAX_VALUE;

        // 자기 자신은 방문한 상태
        Queue<Action> queue = new LinkedList<>();
        queue.add(new Action(N, 0));

        while(!queue.isEmpty()) {
            Action ac = queue.poll();
            if(ac.position > 100000 || ac.position < 0 || isVisited[ac.position]) {
                continue;
            }
            isVisited[ac.position] = true;

            if(ac.position == K) {
                answer = Math.min(ac.time, answer);
                continue;
            }

            queue.add(new Action(ac.position * 2, ac.time + 1));
            queue.add(new Action(ac.position + 1, ac.time + 1));
            queue.add(new Action(ac.position - 1, ac.time + 1));
        }

        System.out.println(answer);
    }
}
