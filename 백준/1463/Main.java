import java.util.Scanner;

public class Main {
    static int[] checkNum = new int[1000001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(func(N));
    }
    public static int func(int X) {
        if(X < 1) {
            return -1;
        }
        if(checkNum[X] == 0) {
            if(X % 6 == 0) {
                checkNum[X] = Math.min(func(X - 1), Math.min(func(X / 2), func(X / 3))) + 1;
            }
            else if(X % 3 == 0) {
                checkNum[X] = Math.min(func(X / 3), func(X - 1)) + 1;
            }
            else if(X % 2 == 0) {
                checkNum[X] = Math.min(func(X / 2), func(X - 1)) + 1;
            } else {
               checkNum[X] = func(X - 1) + 1;
            }
        }
        return checkNum[X];
    }
}
