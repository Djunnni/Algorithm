import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ_2309_일곱_난쟁이
 * author djunnni
 * 
 * 풀이 방법
 * 9난쟁이의 숫자들을 오름차순 정렬해두고 조합으로 문제를 해결합니다.
 * 이때, 한 번 100인 숫자가 나오면 그걸로 끝나게 isFinish를 설정.
 */
public class Main {
    static int numbers[];
    static boolean isFinish;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[9];
        for(int i = 0; i < 9; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        numbers = new int[7];

        comb(arr, 0, 0, 0);

    }
    public static void comb(int[] arr, int cnt, int start, int score) {
        if(7 == cnt) {
            if(score == 100) {
                for(int x : numbers) {
                    System.out.println(x);
                }
                isFinish = true;
            }
            return;
        }

        for(int i = start; i < 9 && !isFinish; i++) {
            numbers[cnt] = arr[i];
            comb(arr, cnt + 1, i + 1, score + numbers[cnt]);
        }
    }
}
