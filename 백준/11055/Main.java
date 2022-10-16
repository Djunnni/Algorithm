package acmicpc.BJ_11055;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_11055_가장 큰 증가 부분 수열
 * author djunnni
 * LIS를 구하는게 아니라 가장 큰 증가 부분수열을 찾아야한다.
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

        int d[] = new int[N];
        int max = -1;
        for (int i = 0; i < N; i++) {
            d[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && d[i] < d[j] + arr[i]) {
                    d[i] = d[j] + arr[i];
                }
            }
            max = Math.max(d[i], max);
        }

        System.out.println(max);
    }
}
