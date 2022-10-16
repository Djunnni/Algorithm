package acmicpc.BJ_10972;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_10972_다음 순열
 * author djunnni
 * Next Permutation
 */
public class Main {
    static int N, arr[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = Integer.parseInt(sc.nextLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        while (np(arr)) {
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            System.out.println(sb.toString().trim());
            return;
        }

        System.out.println(-1);
    }

    private static boolean np(int[] arr) {
        int i = N - 1;

        while (i > 0 && arr[i - 1] >= arr[i])
            --i;

        if (i == 0) { // 이미 해당위치가 꼭대기
            return false;
        }
        int j = N - 1;
        while (arr[i - 1] >= arr[j])
            --j; // i-1보다 큰 j를 찾는다.

        swap(arr, i - 1, j);

        int k = N - 1;
        while (i < k) {
            swap(arr, i++, k--);
        }

        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
