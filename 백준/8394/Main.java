import java.util.Scanner;

// 악수를 할수도 안할 수도 있다.
// 3명이 악수

//        0 0 x
//        1 1 o
//
//        0 0 0 x x
//        1 1 0 o x
//        0 1 1 x o
//
//        0 0 0 0 x x x
//        1 1 0 0 o x x
//        1 1 1 1 o x o
//        0 0 1 1 x x o
//        0 1 1 0 x o x
//
//        0 0 0 0 0 x x x x
//        1 1 0 0 0 o x x x
//        1 1 1 1 0 o x o x
//        1 1 0 1 1 o x x o
//        0 1 1 1 1 x o x o
//        0 1 1 0 0 x o x x
//        0 0 1 1 0 x x o x
//        0 0 0 1 1 x x x o

//        o 옆에는 x만 나올 수 있다
//        x 옆에는 o, x 둘다 나올 수 있다.

//        N명이 오면 N - 1 = b개의 관계선이 있다.
//        먼저 악수를 한다고 가정하면 b - 2개의 관계선이 있다.
//        악수를 하지 않는다면 b - 1개의 관계선이 있다.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 사람 수

        int arr[] = new int[N + 1];

        arr[1] = 0;
        if(N == 1) {
            System.out.println(0);
            return;
        }
        arr[2] = 2;
        if(N == 2) {
            System.out.println(2);
            return;
        }
        arr[3] = 3;
        if(N == 3) {
            System.out.println(3);
            return;
        }
        for(int i = 4; i <= N; i++) {
            arr[i] = (arr[i - 2] + arr[i - 1]) % 10;
        }

        System.out.println(arr[N]);
    }
}
