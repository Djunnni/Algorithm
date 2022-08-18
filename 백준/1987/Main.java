import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_1987_알파벳
 * 2초, 256MB
 * 
 * 알파벳 방문 이력을 Set으로 관리할 지, 배열로 관리할 지 고민하다가
 * 나오는 문자들이 A - Z 내여서 26개의 칸을 저장하는 배열로 boolean 상태를 판별합니다.
 * 
 * author djunnni
 */
public class Main {
    static int R,C, answer;
    static char matrix[][];
    static int dx[] = {0, 0, 1, -1}; //상하우좌
    static int dy[] = {-1, 1, 0, 0};
    static boolean alphabet[];
    static boolean visited[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 세로 R, 가로 C
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // matrix 값 넣기
        matrix = new char[R][C];
        visited = new boolean[R][C];
        alphabet = new boolean[26];  // A ~ Z 26개
        for(int i = 0; i < R; i++) {
            String input = br.readLine();
            for(int j = 0; j < C; j++) {
                matrix[i][j] = input.charAt(j);
            }
        }

        // 말이 처음 위치한 곳도 포함한다.
        answer = 0;

        // 말은 상하좌우로 인접한 4칸중 한칸으로 이동할 수 있으며, 새로 이동한 칸에 적힌 알파벳은 지금까지 지나온 모든 칸에
        // 적혀있는 알파벳과는 달라야 한다.
        // 말이 최대한 몇칸을 지날 수 있는지 프로그램을 작성하시오.
        // 말이 지나온 곳도 포함입니다.
        visited[0][0] = true;
        alphabet[matrix[0][0] - 'A'] = true;
        horseRiding(0, 0, 1);

        System.out.println(answer);
    }
    public static void horseRiding(int x, int y, int cnt) {
        answer = Math.max(answer, cnt);

        for(int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < C && ny >= 0 && ny < R && !alphabet[matrix[ny][nx] - 'A'] && !visited[ny][nx]) {
                alphabet[matrix[ny][nx] - 'A'] = true;
                horseRiding(nx, ny, cnt + 1);
                alphabet[matrix[ny][nx] - 'A'] = false;
            }
        }
    }
}
