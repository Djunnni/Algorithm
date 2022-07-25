import java.util.Arrays;
import java.util.Scanner;

class Solution
{
    static int SIZE = 100;
    public static void main(String args[]) throws Exception
    {
		
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int caseNum = sc.nextInt();
            int[][] matrix = new int[SIZE][SIZE];
            for(int i = 0; i <SIZE; i++) {
                for(int j = 0; j < SIZE; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }

            int MAX_SUM = Integer.MIN_VALUE;

            for(int i = 0; i < SIZE; i++) {
                // 가로축
                int axis_x_sum = Arrays.stream(matrix[i]).sum();
                if(MAX_SUM < axis_x_sum) {
                    MAX_SUM = axis_x_sum;
                }

                // 세로축
                int axis_y_sum = 0;
                for(int j = 0; j < SIZE; j++) {
                    axis_y_sum += matrix[j][i];
                }
                if(MAX_SUM < axis_y_sum) {
                    MAX_SUM = axis_y_sum;
                }
            }

            int under_x_y = 0;
            int upper_x_y = 0;
            for(int i = 0; i < SIZE; i++) {
                under_x_y += matrix[i][i];
                upper_x_y += matrix[(SIZE - 1)- i][i];
            }

            MAX_SUM = Math.max(under_x_y, Math.max(upper_x_y, MAX_SUM));
            System.out.println("#" + test_case + " " + MAX_SUM);
        }
    }
}