import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        ArrayList<Integer> queue = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            queue.add(i);
        }
        func(queue, K);
    }
    public static void func(ArrayList<Integer> queue, int K) {
        System.out.print("<");
        int beforeIndex = (K - 1) % queue.size();
        int value = queue.remove(beforeIndex);
        System.out.print(value);
        while(!queue.isEmpty()) {
            beforeIndex = (beforeIndex + K - 1) % queue.size();
            value = queue.remove(beforeIndex);
            System.out.print(", " + value);
        }
        System.out.print(">");
    }
}