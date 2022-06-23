import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        LinkedList<Integer> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            int key = sc.nextInt();
            if(key == 0) {
                list.add(i);
            } else {
                list.add(i - key - 1, i);
            }
        }
        for(int i = 0; i < list.size(); i ++) {
            sb.append(list.get(i)).append(' ');
        }
        System.out.println(sb);
    }
}
