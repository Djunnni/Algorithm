import java.util.Scanner;

public class AppleTree {
    // 우, 좌, 상, 하
    static int[] dx = { 1, -1, 0, 0};
    static int[] dy = { 0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] matrix = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        // 배열에 값 넣기
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // 정답 0 초기화
        int answer = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int v = matrix[i][j];
                for(int k = 0; k < dx.length; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if( x >= 0 && x < N && y >= 0 && y < N) {
                        v += matrix[x][y];
                    }
                }
                if(v >= answer) {
                    answer = v;
                }
            }
        }



        // 정답 출력
        System.out.println(answer);
    }
}
