import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * SW_2819_격자판의 숫자 이어 붙이기
 * 
 * 2^14에 4 x 4 => 2^16승의 경우가 있을 수 있음.
 * 실행시간 0.3 - 0.4초 이하로 나올것으로 보임. tc 10개이므로 4초 아래로 나옴.
 * author djunnni
 */
public class Solution {
    static int[] dx = { -1, 1, 0, 0 }; // 좌, 우, 상, 하
    static int[] dy = { 0, 0, -1 ,1};
    static int MOVE_COUNT = 6;
    static HashSet<String> set;
    static char[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int answer = 0;

            set = new HashSet<>();
            matrix = new char[4][4];

            for(int i = 0; i < 4; i++) {
                String data = br.readLine();
                for(int j = 0, index = 0; j < 4; j++, index += 2) {
                    matrix[i][j] = data.charAt(index);
                }
            }
//            for(char [] row : matrix) {
//                System.out.println(Arrays.toString(row));
//            }
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    trace(i, j, 0, "");
                }
            }

            out.append("#").append(tc).append(" ").append(set.size()).append("\n");
        }
        System.out.println(out);
    }
    public static void trace(int r, int c, int cnt, String data) {
        if(cnt == MOVE_COUNT) {
            data += matrix[r][c] + "";
            set.add(data);
            return;
        }

        for(int i = 0; i < dx.length; i++) {
            int nextR = r + dy[i];
            int nextC = c + dx[i];

            if(isOver(nextR, nextC)) {
                continue;
            }

            trace(nextR, nextC, cnt + 1, data + matrix[r][c]);
        }
    }
    public static boolean isOver(int r, int c) {
        if(r >= 4 || r < 0 || c >= 4 || c < 0) {
            return true;
        }
        return false;
    }
}
