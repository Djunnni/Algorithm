import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * dongbinna_큰_수의_법칙
 * p92
 * author djunnni
 * O(nlogn) => Arrays.sort 때문
 */
public class 큰_수의_법칙 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // N개의 숫자
        int M = Integer.parseInt(st.nextToken()); // M번을 더하기
        int K = Integer.parseInt(st.nextToken()); // 연속 K번 초과 불가

        int[] arr = new int[N];

        st = new StringTokenizer(sc.nextLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 오름차순

        int max = arr[N - 1];
        int max2 = arr[N - 2];

        // int sum = getSum(M, K, max, max2); // [1]
        int sum = getSum2(M, K, max, max2); // [2]

        System.out.println(sum);
    }

    static int getSum2(int M, int K, int first, int second) {
        int sum = 0;

        int loop = M / (K + 1) * K;
        loop += M % (K + 1);

        sum += loop * first;
        sum += (M - loop) * second;

        return sum;
    }

    static int getSum(int M, int K, int first, int second) {
        int sum = 0;
        while (M > 0) {
            if (K <= M) {
                sum += first * K;
                M -= K;
                if (M == 0)
                    break;
                sum += second;
                M -= 1;
            } else {
                sum += first * M;
                M -= M;
            }
        }

        return sum;
    }
}
/*
 * 5 7 2
 * 3 4 3 4 3
 * => 28
 * 
 * 5 8 3
 * 2 4 5 4 6
 * => 46
 */