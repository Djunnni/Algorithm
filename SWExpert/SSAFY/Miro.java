import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Miro {
    static int[] dx = {0, -1, 0, 1, 0 };
    static int[] dy = {0, 0, 1, 0, -1 };
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int t = 1; t <= T; t++) {
            int SIZE = sc.nextInt();
            int spotX = sc.nextInt();
            int spotY = sc.nextInt();
            int N = sc.nextInt();

            // 배열 사이즈는 SIZE + 1이다. (SIZE까지 접근 가능하게 하려고)
            int[][]  matrix =  new int[SIZE + 1][SIZE + 1];

            // 점퍼는 1로 설정하고 아닐 경우는 0이다.
            for(int i = 0; i < N; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                matrix[x][y] = 1;
            }

            int actionCount = sc.nextInt();
            boolean finish = false;
            // 세로 : X, 가로 : Y
            for(int i = 0; i < actionCount && !finish; i++) {
                // 상 : 1, 오 : 2, 하 : 3, 좌 : 4
                int action = sc.nextInt();
                int count = sc.nextInt();


                // finish로 설정되면 for문 나가기
                for(int a = 1; a <= count && !finish; a++) {
                    int nextX = spotX + dx[action];
                    int nextY = spotY + dy[action];

                    if(nextX >= 1 && nextX <= SIZE && nextY >= 1 && nextY <= SIZE) {
                        spotX = nextX; spotY = nextY;
                    } else {
                        spotX = 0; spotY = 0; finish = true;
                    }

                    if(matrix[spotX][spotY] == 1) {
                        spotX = 0; spotY = 0; finish = true;
                    }
                }

            }


            System.out.println("#"  + t + " " + spotX + " " + spotY);
        }

    }
}
