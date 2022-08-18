import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_3109_빵집
 * author djunnni
 */
public class Main {
    public static int answer = 0, R, C;
    public static char[][] matrix;
    public static int dx[] = { 1, 1, 1}; // 우위, 우, 우하
    public static int dy[] = { -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        matrix = new char[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                matrix[i][j] = input.charAt(j);
            }
        }

        for(int i = 0; i < R; i++) {
            connectPipe(0, i);
        }
        System.out.println(answer);
    }
    public static boolean connectPipe(int column, int row) {
        if(isOver(column, row) || matrix[row][column] != '.') {
            return false;
        }
        matrix[row][column] = 'v';
        // 마지막 컬럼에 도착했을 때,
        if(column == C - 1) {
            answer++;
            return true;
        }

        boolean isCan = false;
        for(int d = 0; d < dx.length && !isCan; d++) {
            int nx = column + dx[d];
            int ny = row + dy[d];

            if(connectPipe(nx, ny)) {
                isCan = true;
            }
        }
        return isCan;
    }
    static public boolean isOver(int x, int y) {
        if(x < 0 || x >= C || y < 0 || y >= R) {
            return true;
        }
        return false;
    }
}
