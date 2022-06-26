import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner((System.in));
        int T = sc.nextInt();

        for(int i = 0; i < T; i++) {
            System.out.println(checkVPS(sc.next()) ? "YES" : "NO");
        }
    }
    public static boolean checkVPS(String str) {
        Stack<Character> stack = new Stack<>();
        char[] arr = str.toCharArray();
        for(var ch : arr) {
            if(ch == '(') {
                stack.push('(');
            } else if(ch == ')'){
                try {
                    stack.pop();
                } catch(Exception e) {
                    return false;
                }
            }
        }
        if(!stack.isEmpty()) return false;
        return true;
    }
}
