import java.util.Scanner;
import java.util.Stack;

public class Solution_9_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        String[] data = sc.nextLine().split(" ");

        int answer = 0;
        int v1 = 0, v2 = 0;
        for(int i = 0; i < data.length; i++) {
            switch(data[i]) {
                case "+":
                    v1 = stack.pop();
                    v2 = stack.pop();
                    stack.add(v1 + v2);
                    break;
                case "-":
                    v1 = stack.pop();
                    v2 = stack.pop();
                    stack.add(v2 - v1);
                    break;
                case "*":
                    v1 = stack.pop();
                    v2 = stack.pop();
                    stack.add(v2 * v1);
                    break;
                case "/":
                    v1 = stack.pop();
                    v2 = stack.pop();
                    stack.add(v2 / v1);
                    break;
                default:
                    stack.push(Integer.parseInt(data[i]));
            }
        }
        answer = stack.pop();
        System.out.println(answer);
    }
}
