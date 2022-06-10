package chapter4_8;

import java.util.Scanner;

public class Solution4_6 {
    public static boolean func(int i, int w, int[] arr, boolean[] tempArr) {
        if(i == 0) {
            if(w == 0) return true;
            else return false;
        }
        if(tempArr[i - 1]) {
            return true;
        }
        if(func(i - 1, w, arr, tempArr)) return tempArr[i - 1] = true;
        if(func(i - 1, w - arr[i - 1], arr, tempArr)) return tempArr[i - 1] = true;

        return tempArr[i - 1] = false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int W = sc.nextInt();

        int[] arr = new int[N];
        boolean[] tempArr = new boolean[N];
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        if(func(N,W,arr, tempArr)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
