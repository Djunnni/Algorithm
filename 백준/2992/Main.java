import java.util.Scanner;

/**
 * acmicpc.BJ_2992_크면서_작은_수
 * author djunnni
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int arr[] = new int[(N + "").length()];

        char[] temp = (N + "").toCharArray();
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i] - '0';
        }

        StringBuilder sb = new StringBuilder();

        while (np(arr)) {
            for (int n : arr) {
                sb.append(n);
            }
            System.out.println(sb.toString());
            return;
        }

        System.out.println(0);
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
