import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BJ_17070_파이프_옮기기1
 * author djunnni
 * 
 * 문제 풀이
 * 
 * 처음에 BFS로 접근했더니 89%에서 시간초과가 발생하는 경우를 확인
 * 어느정도 정답에 근거했다고 판단해 DFS로 변경
 * 
 * BFS와 DFS의 시간차이에 대해서 좀 알아봐야겠다.
 */
public class Main {
    static int [][] moves = {{1, 0}, {1, 1}, {0, 1}}; // 우, 우하, 하
    static int answer, N;
    static char[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 행과 열의 번호는 1번부터 시작한다.
        // 빈칸은 0, 벽은 1로 주어진다.
        matrix = new char[N + 1][N + 1];

        for(int r = 1; r <= N; r++) {
            String input = br.readLine();
            for(int c = 1, index = 0; c <= N; c++, index += 2) {
                matrix[r][c] = input.charAt(index);
            }
        }

        // 맵 출력
//        for(char[] row : matrix) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println("------------------");


//        bfs(1, 1, 1, 2);
        dfs(1, 1, 1, 2);
        System.out.println(answer);
    }
    public static void dfs(int startR, int startC, int endR, int endC) {
        // endR, endC가 N의 범위를 벗어나면 다음 파이프를 확인한다.
        if(endR > N || endC > N) {
            return;
        }
        // endR, endC 지점이 벽이라면 다음 파이프를 확인한다.
        if(matrix[endR][endC] != '0') {
            return;
        }
        // System.out.println(Arrays.toString(pipe));
        // 0 -> 다음으로 0, 1, 1 -> 다음으로 0, 1, 2, 2 -> 다음으로 1, 2
        // 기본 가로
        int currentMode = 0;
        if(endR > startR && endC > startC) {
            // 대각선
            currentMode = 1;
            if(matrix[endR - 1][endC] != '0' || matrix[endR][endC - 1] != '0') {
                return;
            }
        } else if(startC == endC && startR < endR) {
            // 세로
            currentMode = 2;
        }
        // endR, endC가 N,N에 도착했을 때, answer를 1더하고 다음 파이프를 확인한다.
        if(endR == N && endC == N && matrix[endR][endC] == '0') {
            answer++;
            return;
        }


        for(int d = 0; d < moves.length; d++) {
            int nextR = endR + moves[d][1];
            int nextC = endC + moves[d][0];

            if(currentMode == 0 && (d == 0 || d == 1)) {
                dfs(endR, endC, nextR, nextC);
            } else if(currentMode == 1) {
                dfs(endR, endC, nextR, nextC);
            } else if(currentMode == 2 && (d == 1 || d == 2)) {
                dfs(endR, endC, nextR, nextC);
            }
        }
    }
//    public static void bfs(int startR, int startC, int endR, int endC) {
//        Queue<int[]> queue = new LinkedList<>();
//        queue.add(new int[]{ startR, startC, endR, endC });
//        // startR과 startC는 다음 지점의 endR과 endC가 된다.
//        // 신경쓸 건 endR과 endC만!
//        while(!queue.isEmpty()) {
//            int[] pipe = queue.poll();
//            // endR, endC가 N의 범위를 벗어나면 다음 파이프를 확인한다.
//            if(pipe[2] > N || pipe[3] > N) {
//                continue;
//            }
//            // endR, endC 지점이 벽이라면 다음 파이프를 확인한다.
//            if(matrix[pipe[2]][pipe[3]] != '0') {
//                continue;
//            }
//            // System.out.println(Arrays.toString(pipe));
//            // 0 -> 다음으로 0, 1, 1 -> 다음으로 0, 1, 2, 2 -> 다음으로 1, 2
//            // 기본 가로
//            int currentMode = 0;
//            if(pipe[2] > pipe[0] && pipe[3] > pipe[1]) {
//                // 대각선
//                currentMode = 1;
//                if(matrix[pipe[2] - 1][pipe[3]] != '0' || matrix[pipe[2]][pipe[3] - 1] != '0') {
//                    continue;
//                }
//            } else if(pipe[1] == pipe[3] && pipe[0] < pipe[2]) {
//                // 세로
//                currentMode = 2;
//            }
//            // endR, endC가 N,N에 도착했을 때, answer를 1더하고 다음 파이프를 확인한다.
//            if(pipe[2] == N && pipe[3] == N && matrix[pipe[2]][pipe[3]] == '0') {
//                answer++;
//                continue;
//            }
//
//            for(int d = 0; d < moves.length; d++) {
//                int nextR = pipe[2] + moves[d][1];
//                int nextC = pipe[3] + moves[d][0];
//
//                if(currentMode == 0 && (d == 0 || d == 1)) {
//                    queue.add(new int[]{ pipe[2], pipe[3], nextR, nextC });
//                } else if(currentMode == 1) {
//                    queue.add(new int[]{ pipe[2], pipe[3], nextR, nextC });
//                } else if(currentMode == 2 && (d == 1 || d == 2)) {
//                    queue.add(new int[]{ pipe[2], pipe[3], nextR, nextC });
//                }
//            }
//        }
//    }
}
