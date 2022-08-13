import java.util.Scanner;

/**
 * ABC045_C
 * 2sec, 256MB
 *
 * author djunnni
 */
public class Solution3_7_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        long answer = 0;

        /**
         * 123, 1 + 23, 12 + 3, 1 + 2 + 3
         * 00, 01, 10, 11
         */
        // bit -> 0, 1, 2, 3
        for(int bit = 0; bit < (1 << input.length() - 1); bit++) {
            // i => 0, 1
            String temp = input.charAt(0) + "";
            for(int i = 0; i < input.length() - 1; i++) {
                if((bit & (1 << i)) != 0) {
                    answer += Long.parseLong(temp);
                    temp = input.charAt(i + 1) + "";
                } else {
                    temp += input.charAt(i + 1) + "";
                }
            }
            answer += Long.parseLong(temp);
        }

        System.out.println(answer);
    }
}
