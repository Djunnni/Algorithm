import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = N; i > 0; i--) {
            queue.addLast(i);
        }

        int answer = 0;
        while(!queue.isEmpty()) {
                if(queue.size() == 1) {
                    answer = queue.getFirst();
                    break;
                }
                queue.removeLast();
                int next = queue.removeLast();
                queue.addFirst(next);
        }

        System.out.println(answer);
    }
}
