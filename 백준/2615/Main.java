import java.util.Scanner;

public class Main {
    static int SIZE = 19;
    static int dx[] = { 1, 0, -1, 1 }; // 하 우  상우  하우
    static int dy[] = { 0, 1, 1, 1 };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 바둑판의 크기는 가로세로 19사이즈이지만 1을 키워서 1~19안에 넣도록 하겠다.
        int matrix[][] = new int[SIZE + 1][SIZE + 1];
       // boolean visited[][] = new boolean[SIZE + 1][SIZE + 1];

        // 1,1 ~ 19,19에 값을 초기화한다.
        for(int i = 1; i <= SIZE; i++) {
            for(int j = 1; j <= SIZE; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int winner = 0; // 1 => 검정, 2 => 흰색
        int spot[] = { 0, 0 };
        boolean isDone = false;
        // i가 상하, j가 좌우
        for(int i = 1; i <= SIZE && !isDone; i++) {
            for(int j = 1; j <= SIZE && !isDone; j++) {
                spot[0] = 0;
                spot[1] = 0;
                // 바둑돌이 i, j 위치에 놓여져 있다면!
                if(matrix[i][j] != 0) {
                    for(int k = 0; k < dx.length && !isDone; k++) {
                        // 자기 자신을 count 하자. cnt = 1
                        int cnt = 1;

                        while(true) {
                            int currentX = i + (dx[k] * cnt);
                            int currentY = j + (dy[k] * cnt);

                            // 범위를 넘지 않으면서 i,j번째가 currentX, currentY가 같다면
                            if(!isOver(currentX, currentY) && matrix[i][j] == matrix[currentX][currentY]) {
                                cnt++;
                            } else {
                                // 그렇지 않다면 빠져나온다.
                                break;
                            }
                        }

                        if(cnt == 5 && matrix[i + (dx[k] * -1)][j + (dy[k] * -1)] != matrix[i][j]) {
                            winner = matrix[i][j];
                            spot[0] = i;
                            spot[1] = j;
                            isDone = true;
                            break;
                        }
                    }

                }
            }
        }



        System.out.println(winner);
        if(winner != 0) {
            System.out.println(spot[0] + " " + spot[1]);
        }
    }
    static public boolean isOver(int x, int y) {
        if(x < 1 || x > SIZE || y < 1 || y > SIZE) {
            return true;
        }
        return false;
    }

}
