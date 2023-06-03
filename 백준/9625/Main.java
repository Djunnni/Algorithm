import java.util.Scanner;

// 화면에는 A만 있음 -> 버튼 누르니 B -> BA -> BAB ->BABBA
// a -> b
// b -> ba
// a -> b -> ba -> bab -> babba
// 1 0 -> 0 1 -> 1 1 -> 1 2 -> 2 3 ->
// a는 이전 b의 개수
// b는 이전 b의 개수 + a의 개수
public class Main_9625 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();

        int A = 1;
        int B = 0;

        for(int i = 1; i <= K; i++) {
            int tempA = A;
            A = B;
            B = tempA + A;
        }

        System.out.println(A + " " + B);
    }
}
