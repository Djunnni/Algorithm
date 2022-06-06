package chapter3_7;

import java.util.Scanner;

public class Solution3_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();

        int count = 0;
        for(int i = 0; i <= K; i++) {
            for(int j = 0; j <= K; j++) {
                int v = N - i - j;
                if(v >= 0 && v <= K) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
