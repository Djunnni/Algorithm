import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_12015_가장 긴 증가하는 부분수열 2
 * author djunnni
 * nlogn => BinarySearch를 이용한 LIS
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

        int C[] = new int[N];

        int size = 0;

        for (int i = 0; i < N; i++) {
            int where = Arrays.binarySearch(C, 0, size, arr[i]);
            if (where < 0) {
                where = Math.abs(where) - 1;
                C[where] = arr[i];
                if (where == size)
                    size++;
            }
        }

        System.out.println(size);
    }
}
