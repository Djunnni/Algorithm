import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution
{
    static int SIZE = 16;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int[][] matrix = new int[SIZE][SIZE];
    static boolean[][] answers = new boolean[SIZE][SIZE];
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int T = sc.nextInt();
            boolean answer = false;

            for(int i = 0; i < SIZE; i++) {
                int j = 0;
                for(String v : sc.next().split("")) {
                    matrix[i][j++] = Integer.parseInt(v);
                }
                Arrays.fill(answers[i], false);
            }
            /////////////////////////////////////////////////////////////////////////////////////////////

            Queue<Integer> xQueue = new LinkedList<>();
            Queue<Integer> yQueue = new LinkedList<>();
            xQueue.add(1);
            yQueue.add(1);
            answers[1][1] = true;

            while(!xQueue.isEmpty()) {
                int x = xQueue.poll();
                int y = yQueue.poll();

                for(int i = 0; i < dx.length; i++) {
                    int next_x = x + dx[i];
                    int next_y = y + dy[i];

                    if(next_x < 0 || next_x >= SIZE  || next_y < 0 || next_y >= SIZE) {
                        continue;
                    }
                    if(matrix[next_x][next_y] == 1) {
                        continue;
                    } else if(matrix[next_x][next_y] == 0 && !answers[next_x][next_y]) {
                        answers[next_x][next_y] = true;
                        xQueue.add(next_x);
                        yQueue.add(next_y);
                    } else if(matrix[next_x][next_y] == 3) {
                        answers[next_x][next_y] = true;
                        answer = true;
                        xQueue.clear();
                        yQueue.clear();
                    }
                }
            }


            /////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("#" + T + " " + (answer ? 1 : 0));

        }
    }
}