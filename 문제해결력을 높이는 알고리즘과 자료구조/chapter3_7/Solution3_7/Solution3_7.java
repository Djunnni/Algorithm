package chapter3_7;

import java.util.Scanner;

public class Solution3_7 {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String Num = sc.nextLine();

        // "125" => 1 + 25, 12 + 5, 125, 1 + 2 + 5 => 176
        // O(N2^N)을 만들어라.

        // 앞자리부터 N까지 돌고 있으니 N
        long sum = 0;

        for(int bit = 0; bit < (1 << Num.length() - 1); bit++) {
            long tempSum = 0;
            int index = 0;
            for(int i = 0; i < Num.length() - 1; i++) {
                int value = Character.getNumericValue(Num.charAt(i));
                if ((bit & (1 << i)) != 0) {
                    tempSum = (tempSum) * 10 + value;
                    sum += tempSum;
                    tempSum = 0;
                    index++;
                } else {
                    tempSum = (10 * tempSum) + value;
                }
            }
            if(index == Num.length() - 1) {
                sum += Character.getNumericValue(Num.charAt(Num.length() - 1));
            } else {
                sum += (tempSum * 10) + Character.getNumericValue(Num.charAt(Num.length() - 1));
            }

        }

        System.out.println(sum);
    }
}
