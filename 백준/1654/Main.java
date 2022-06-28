package solved_ac;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();

        int arr[] = new int[K];

        long max = 0;
        for(int i = 0; i < K; i++) {
            arr[i] = sc.nextInt();
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        long min = 1;
        while(min < max) {
            long mid = (min + max) / 2;
            long count = 0;
            for(int i = 0; i < K; i++) {
                count += (arr[i] / mid);
            }
            if(count <= N) {
                max = mid - 1;
            } else {
                min = mid;
            }
            if(min == mid) {
                break;
            }
        }
        System.out.println(max + 1);
    }
}
