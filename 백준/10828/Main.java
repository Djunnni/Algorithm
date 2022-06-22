import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < N; i++) {
            String command = sc.next();
            switch(command) {
                case "push":
                    stack.push(sc.nextInt());
                    break;
                case "pop":
                    if(stack.isEmpty()) {
                        sb.append(-1 + "\n");
                    } else {
                        sb.append(stack.pop() + "\n");
                    }
                    break;
                case "size":
                    sb.append(stack.size() + "\n");
                    break;
                case "empty":
                    if(stack.isEmpty()) {
                        sb.append(1 + "\n");
                    } else {
                        sb.append(0 + "\n");
                    }
                    break;
                case "top":
                    if(stack.isEmpty()) {
                        sb.append(-1 + "\n");
                    } else {
                        sb.append(stack.lastElement() + "\n");
                    }
                    break;
                default:
            }
        }
        System.out.println(sb);
    }
}
