import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * BJ_2605_줄세우기
 *
 * author djunnni
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 학생 수
        int N = sc.nextInt();
        List<Integer> list = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            int value = sc.nextInt();
            if(list.isEmpty()) {
                list.add(i);
            } else {
                list.add(list.size() - value, i);
            }
        }

        for(int x : list) {
            System.out.print(x + " ");
        }
    }
}
