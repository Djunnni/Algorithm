import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int max = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            queue.add(num);

            if (max < num) { // 최대 숫자 반영하기
                max = num;
            }
        }

        int answer = max - queue.peek();
        while(!queue.isEmpty()) {
            int first = queue.poll();
            if(first > 1_000_000_000 || first <= 0) {
                continue;
            }
            first *= 2;
            if(max < first) {
                max = first;
            }
            queue.add(first);
            answer = Math.min(answer, max - queue.peek());
        }

        System.out.println(answer);
    }

}
