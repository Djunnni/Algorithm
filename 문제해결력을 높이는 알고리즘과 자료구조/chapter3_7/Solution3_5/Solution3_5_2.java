import java.util.Scanner;

/**
 * AtCoder B - Shift Only
 *
 * 2초, 256MB
 *
 * 2로 모든 수열을 몇번이나 나눌 수 있는지 확인하시오.
 * 단, 홀수가 나오면 멈춘다.
 *
 * author djunnni
 */
public class Solution3_5_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr [] = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

       int answer = Integer.MAX_VALUE;

        /**
         * 1 -> 0001, 2 -> 0010, 3 -> 0011
         * A[i]는 최대 10^9이며 N은 200개까지 가능하다.
         */

        for(int i = 0; i < N; i++) {
            int cnt = 0;
            while(true) {
                if((arr[i] & (1 << 0)) != 0 || arr[i] == 0) {
                    break;
                }
                arr[i] = arr[i] >> 1;
                cnt++;
            }
            answer = Math.min(answer, cnt);
        }

        System.out.println(answer);
    }
}
