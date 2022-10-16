package acmicpc.BJ_11722;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_11722_가장 긴 감소하는 부분 수열
 * author djunnni
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int length = 0;
        int LIS[] = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            LIS[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (arr[i] > arr[j] && LIS[i] < LIS[j] + 1) {
                    LIS[i] = LIS[j] + 1;
                }
            }
            length = Math.max(length, LIS[i]);
        }

        System.out.println(length);
    }
}
