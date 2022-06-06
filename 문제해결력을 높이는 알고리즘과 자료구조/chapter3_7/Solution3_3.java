package chapter3_7;

import java.util.Scanner;

public class Solution3_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        if(N < 2) {
            System.out.println("N >= 2 이상이어야 합니다.");
            return;
        }
        int[] a = new int[N];
        for(int i = 0; i < N; i++) {
            a[i] = scanner.nextInt();
        }
        int small = a[0];
        int small2 = a[0];
        for(int i=0; i < N; i++) {
            if(small > a[i]) {
                small2 = small;
                small = a[i];
            }
        }
        System.out.println(small);
        System.out.println(small2);
    }
}
