import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < N; i++) {
            int v = sc.nextInt();
            map.put(v, 1);
        }
        int M = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            int key = sc.nextInt();
            sb.append(map.getOrDefault(key, 0));
            if(M - 1 != i) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
