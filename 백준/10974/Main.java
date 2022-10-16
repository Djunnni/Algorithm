import java.util.Scanner;

/**
 * acmicpc.BJ_10974_모든 순열
 * author djunnni
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int arr[] = new int[N];

        for (int i = 1; i <= N; i++) {
            arr[i - 1] = i;
        }

        do {
            StringBuilder sb = new StringBuilder();
            for (int n : arr) {
                sb.append(n).append(" ");
            }
            System.out.println(sb.toString());
        } while (np(arr));

    }

    private static boolean np(int[] arr) {
        int N = arr.length;

        int i = N - 1;
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;

        if (i == 0) { // 이미 최대
            return false;
        }

        int j = N - 1;
        while (arr[i - 1] >= arr[j])
            j--;

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
