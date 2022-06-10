package chapter4_8;

import java.util.ArrayList;

public class Solution4_2 {
    public static long fibo(int n, long[] arr) {
        if(n == 0 || n == 1) {
            return 0;
        } else if(n == 2) {
            return 1;
        }
        if(arr[n] != 0) {
            return arr[n];
        }
        return arr[n] = fibo(n-1, arr) + fibo(n-2, arr) + fibo(n-3, arr);
    }
    public static void main(String[] args) {
        long[] arr = new long[2001];
        long time = System.currentTimeMillis();
        System.out.println(fibo(2000, arr));
        System.out.println(System.currentTimeMillis() - time);
    }
}
