import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OctopusMove {
    // 1 ~ 8 까지 이동경로
    static int dx[] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int dy[] = {0, 0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/com/ssafy/input_octopus.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int t = 1; t <= T; t++) {
            int answer = 0;
            int X = sc.nextInt();
            int Y = sc.nextInt();
            int N = sc.nextInt();

            // 2차원 배열
            int[][] matrix = new int[X + 1][Y + 1];
            // 참가자들의 지갑
            int[][] wallets = new int[N + 1][1];

            // 게임에 참가할 때, -1000원씩
            for(int i = 1; i < N + 1; i++) {
                wallets[i][0] = -1000;
            }

            // 2차원 배열 초기화
            for(int i = 1; i <= X; i++) {
                for(int j = 1; j <= Y; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }

            // 유저당 spotX, spotY, count를 세긴다.
            for(int i = 1; i <= N; i++) {
                int spotX = sc.nextInt();
                int spotY = sc.nextInt();
                int count = sc.nextInt();

                // 방향
                for(int j = 0; j < count; j++) {
                    // 방향과 몇번 움직일지를 구한다.
                    int direction = matrix[spotX][spotY] / 10;
                    int move = matrix[spotX][spotY] % 10;

                    spotX += (dx[direction] * move);
                    spotY += (dy[direction] * move);

                    // 범위를 벗어났다면 체크해주기
                    if(spotX >= 1 && spotX <= X && spotY >= 1 && spotY <= Y) {

                    } else {
                        spotX = -1; spotY = -1; break;
                    }
                }
                // spot이 벗어나지 않았다면 지갑에 돈 충전
                if(spotX != -1 && spotY != -1)
                    wallets[i][0] += matrix[spotX][spotY] * 100;
            }

            for(int[] arr : wallets) {
                answer += arr[0];
            }
            System.out.println("#" + t + " " + answer);
        }
    }
}
 {
    
}
