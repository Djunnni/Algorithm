import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> queue = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            String command = sc.next();
            switch(command) {
                case "push":
                    queue.add(sc.nextInt());
                    break;
                case "pop":
                    if(queue.isEmpty()) {
                        sb.append(-1 + "\n");
                    } else {
                        sb.append(queue.remove(0) + "\n");
                    }
                    break;
                case "size":
                    sb.append(queue.size() + "\n");
                    break;
                case "empty":
                    if(queue.isEmpty()) {
                        sb.append(1 + "\n");
                    } else {
                        sb.append(0 + "\n");
                    }
                    break;
                case "front":
                    if(queue.isEmpty()) {
                        sb.append(-1 + "\n");
                    } else {
                        sb.append(queue.get(0) + "\n");
                    }
                    break;
                case "back":
                    if(queue.isEmpty()) {
                        sb.append(-1 + "\n");
                    } else {
                        sb.append(queue.get(queue.size() - 1) + "\n");
                    }
                    break;
                default:
            }
        }
        System.out.println(sb);
    }
}
