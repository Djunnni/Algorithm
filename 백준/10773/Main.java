import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < K; i++) {
            int v = sc.nextInt();
            if(v != 0) {
                stack.add(v);
            } else {
                if(!stack.isEmpty())
                    stack.pop();
            }
        }
        int answer = 0;
        for(int value : stack) {
            answer += value;
        }
        System.out.println(answer);
    }
}
