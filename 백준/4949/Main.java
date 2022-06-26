import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean again = true;

        StringBuilder sb = new StringBuilder();

        while(again) {
            Stack<Character> arr = new Stack<>();
            String str = sc.nextLine();
            if(str.equals(".")) {
                again = false;
                continue;
            }

            try {
                for(char ch : str.toCharArray()) {
                    if (ch == '[') {
                        arr.add(ch);
                    } else if (ch == ']') {
                        if(arr.peek() == '[') {
                            arr.pop();
                        } else {
                            throw new Exception();
                        }
                    } else if (ch == '(') {
                        arr.add(ch);
                    } else if (ch == ')') {
                        if(arr.peek() == '(') {
                            arr.pop();
                        } else {
                            throw new Exception();
                        }
                    }
                }
            } catch(Exception e) {
                sb.append("no").append('\n');
                continue;
            }

            if(arr.isEmpty()) {
                sb.append("yes").append('\n');
            } else {
                sb.append("no").append('\n');
            }
        }
        System.out.println(sb);
    }
}
