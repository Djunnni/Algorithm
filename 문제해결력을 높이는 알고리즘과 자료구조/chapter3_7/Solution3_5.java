package chapter3_7;

import java.util.Scanner;

/*
    N개의 양의 정수가 주어진 배열이 있다.
    N개의 정수가 모두 짝수라면 2로 나눈값으로 치환하는 작업을 더이상 할 수 없을 때까지 반복한다.
    이런작업을 몇번을 해야하는지 알고리즘을 설계하시오.
    AtCoder Beginner Contest 081
 */

public class Solution3_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] a = new int[N];
        int bit = 0;
        for(int i = 0; i < N; i++) {
            bit |= scanner.nextInt();
        }
        System.out.println(Integer.numberOfTrailingZeros(bit));
    }

    public static void mySolve(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] a = new int[N];
        for(int i = 0; i < N; i++) {
            a[i] = scanner.nextInt();
        }

        int count = 0;
        boolean status = true;
        while(status) {
            for(int i = 0; i < N; i++) {
                if(a[i] % 2 != 0) {
                    status = false;
                }
            }
            for(int i = 0; i < N && status; i++) {
                a[i] /= 2;
            }
            if(status) {
                count++;
            }
        }
        System.out.println(count);
    }
}
