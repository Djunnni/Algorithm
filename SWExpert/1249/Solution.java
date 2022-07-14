import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
		
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		
        for(int test_case = 1; test_case <= T; test_case++)
        {
            // n 배열 크기를 받아 matrix에 넣어준다.
            int N = sc.nextInt();
            
            // 저장할 배열과 정답이 들어갈 배열을 생성
            int[][] matrix = new int[N][N];
            int[][] answer = new int[N][N];

            // case로 주어진 값을 matrix에 넣기
            for(int i = 0; i < N; i++) {
                int j = 0;
                for(String v : sc.next().split("")) {
                    matrix[i][j++] = Integer.parseInt(v);
                }
            }

            // 정답 배열에는 MAX_VALUE로 변경;
            for(int i = 0; i < N; i++) {
                Arrays.fill(answer[i], Integer.MAX_VALUE);
            }
            // [0][0]은 방문했다고 하자.
            answer[0][0] = matrix[0][0];
            // 돌아다니기
            trace(matrix, answer);

            // 정답 출력
            System.out.println("#" + test_case + " " + answer[N-1][N-1]);
        }
    }
    public static void trace(int[][] matrix, int[][] answer) {
        // x,y의 좌표가 이동할 수 있을 케이스들을 작성
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // queue로 방문할 지점들을 저장
        Queue<Integer> xQueue = new LinkedList<Integer>();
        Queue<Integer> yQueue = new LinkedList<Integer>();

        // [0][0] 방문이라고 치자
        xQueue.add(0);
        yQueue.add(0);

        while(!xQueue.isEmpty()) {
            // x,y를 꺼내어 next와 비교하는 로직 추가
            int x = xQueue.poll();
            int y = yQueue.poll();

            for(int k = 0; k < dx.length; k++) {
                int next_x = x + dx[k];
                int next_y = y + dy[k];
                // 경계조건 넘으면 continue;
                if(next_x < 0 || next_x >= matrix.length || next_y < 0 || next_y >= matrix.length) {
                    continue;
                }

                // 경계조건에 맞고 next_x,next_y의 값이 전 위치에서의 합보다 작으면 업데이트 + 방문
                if(answer[next_x][next_y] > answer[x][y] + matrix[next_x][next_y]) {
                    answer[next_x][next_y] = answer[x][y] + matrix[next_x][next_y];
                    xQueue.add(next_x);
                    yQueue.add(next_y);
                }
            }
        }
    }
}