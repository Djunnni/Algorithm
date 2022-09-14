import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * acmicpc.BJ_12852_1로만들기2_백트래킹
 * 
 * 가지치기 전략으로 문제 해결
 * 
 * author djunnni
 */
public class Main {
    static int N, answer;
    static List<Integer> answerList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 숫자 N
        N = sc.nextInt();

        answer = Integer.MAX_VALUE;

        makeOne(N, 0, new ArrayList<Integer>());

        System.out.println(answer);
        for (int a : answerList) {
            System.out.print(a + " ");
        }
    }

    public static void makeOne(int N, int count, List<Integer> list) {
        if (N == 1) {
            if (answer > count) {
                answer = count;
                list.add(1);
                answerList = list;
            }
            return;
        } else if (N < 1) {
            return;
        }

        if (answer < count) {
            return;
        }

        List<Integer> temp = new ArrayList<>(list);
        temp.add(N);

        if (N % 3 == 0) {
            makeOne(N / 3, count + 1, new ArrayList<>(temp));
        }

        if (N % 2 == 0) {
            makeOne(N / 2, count + 1, new ArrayList<>(temp));
        }

        makeOne(N - 1, count + 1, new ArrayList<>(temp));
    }
}
