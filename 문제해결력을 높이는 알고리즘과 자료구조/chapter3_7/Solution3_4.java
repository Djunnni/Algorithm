package chapter3_7;

import java.util.Scanner;

public class Solution3_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] a = new int[N];
        for(int i = 0; i < N; i++) {
            a[i] = scanner.nextInt();
        }
        int max = a[0];
        int min = a[0];
        for(int i=0; i < N; i++) {
           if(max < a[i]) {
               max = a[i];
           }
           if(min > a[i]) {
               min = a[i];
           }
        }
        System.out.println(max - min);

    }
}
