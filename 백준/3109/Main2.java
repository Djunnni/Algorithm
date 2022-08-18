import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_3109_빵집
 * 직접적으로 우상, 우, 우하를 넣어 true가 나오면 부모도 true를 리턴
 * 반복해도 안될 경우엔 false;
 * author djunnni
 */
public class Main2 {
    public static int answer = 0, R, C;
    public static char[][] matrix;
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
            if(connectPipe(0, i)) {
                answer++;
            }
        }
//        for(char x[] : matrix)
//            System.out.println(x);
        System.out.println(answer);
    }
    public static boolean connectPipe(int column, int row) {
        matrix[row][column] = 'v';
        // 마지막 컬럼에 도착했을 때,
        if(column == C - 1) {
            return true;
        }

        if(row > 0 && matrix[row - 1][column + 1] == '.') {
            if(connectPipe(column + 1, row - 1)) {
                return true;
            }
        }

        if(matrix[row][column + 1] == '.') {
            if(connectPipe(column + 1, row)) {
                return true;
            }
        }

        if(row + 1 < R && matrix[row + 1][column + 1] == '.') {
            if(connectPipe(column + 1, row + 1)) {
                return true;
            }
        }

        return false;
    }
}
