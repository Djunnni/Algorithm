import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ_2839_설탕배달
 * 1초, 128MB
 *
 * author 이동준
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int count = 0; int answer = 0;

        while(N >= 0) {
            if(N % 5 == 0) {
                answer = N / 5 + count;
                break;
            }
            N -= 3;
            count += 1;
        }

        if(N < 0) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
    // 차음에 푼 방식
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);

    //     int N = sc.nextInt();

    //     int dp[] = new int[N + 1]; // 3 ~ 5000
    //     Arrays.fill(dp, -1);

    //     for(int i = 0; i <= N; i++) {
    //         int current = i;
    //         if(current % 5 == 0) {
    //             dp[i] = current / 5;
    //             continue;
    //         } else {
    //             int cnt = current / 5;
    //             current %= 5;
    //             while(current % 3 != 0 && cnt > 0) {
    //                 current += 5;
    //                 cnt--;
    //             }
    //             dp[i] = cnt;
    //         }

    //         if(current % 3 == 0) {
    //             dp[i] += current / 3;
    //             continue;
    //         }

    //         dp[i] = -1;
    //     }
    //     System.out.println(dp[N]);
    // }
}
