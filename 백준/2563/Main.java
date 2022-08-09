import java.util.Scanner;

/**
 * BJ_2563_색종이
 *
 * author djunnni
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 박스 개수

        int matrix[][] = new int[101][101]; // 1 <= 크기 <= 100

        for(int n = 0; n < N; n++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < 10; j++) {
                    matrix[y + i][x + j] = 1;
                }
            }
        }
        int answer = 0;
        for(int i = 1; i <= 100; i++) {
            for(int j = 1; j <= 100; j++) {
                if(matrix[j][i] == 1)
                    answer++;
            }
        }
        System.out.println(answer);
    }
}
