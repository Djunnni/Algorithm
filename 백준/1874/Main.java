import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            list.add(sc.nextInt());
        }
        boolean canAccess = true;
        ArrayList<String> results = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        // [4, 3, 6, 8, 7, 5, 2, 1]

        int v = 1;
        while(!list.isEmpty()) {
            int first = list.get(0);
            // i가 first보다 작으면 stack에 추가
            if(v <= first) {
                stack.push(v);
                results.add("+");
                v++;
            }

            int stackLast = stack.lastElement();
            if(stackLast == first) {
                stack.pop();
                results.add("-");
                list.remove(0);
            } else if(stackLast > first) {
                canAccess = false;
                break;
            }
        }
        if(canAccess) {
            for(var str : results) {
                System.out.println(str);
            }
        } else {
            System.out.println("NO");
        }

    }
}
