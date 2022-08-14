import java.io.ByteArrayInputStream;
import java.util.*;

public class Solution_9_3 {
    public static String data = "(()(())())(()())";
    public static void main(String[] args) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // index를 저장해 두는 스택
        Stack<Integer> stack = new Stack<>();
        List<int[]> arr = new LinkedList<>();
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '(') {
                stack.add(i);
            } else {
                int temp[] = new int[2];
                temp[0] = stack.pop();
                temp[1] = i;
                arr.add(temp);
            }
        }
        if(stack.isEmpty()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        for(int[] t : arr) {
            System.out.println(Arrays.toString(t));
        }
    }
}
