package chapter3_7;

import java.util.Scanner;

public class Solution3_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int[] a = new int[N];
        for(int i = 0; i < N; i++) {
            a[i] = scanner.nextInt();
        }

        int found_id = -1;
        for(int i = 0; i < N; ++i) {
            if(a[i] == V) {
                found_id = i;
            }
        }

        System.out.println(found_id);
    }
}
