import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 3. 화산탄
 * @author 광주_5반 이동준
 */
public class Main {
    // 맵
    public static char[][] map;
    public static Stack<Point>[] dp;
    // 맵 row, column
    public static int R, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens = new StringTokenizer(br.readLine(), " ");

        // Map의 크기 R과 C ( 1 <= R <= 30,000, 1 <= C <= 30)
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        // 맵 초기화 & 초기 상태 저장하기 // . : 빈칸, X : 장애물
        map = new char[R + 1][C + 1];

        // 갈수있는 경로를 미리 저장할 수 있는 내역을 넣습니다.
        dp = new Stack[C + 1];
        for(int i = 1; i <= C; i++) {
            dp[i] = new Stack<>();
        }

        for(int line = 1; line <= R; line++) {
            String input = br.readLine();
            for(int column = 1; column <= C; column++) {
                map[line][column] = input.charAt(column - 1);
            }
        }

        // 맵 배열 확인
//		for(char[] row : map) {
//			System.out.println(Arrays.toString(row));
//		}
//		System.out.println("======================");

        // 화산탄이 날라올 횟수 ( 1 <= N <= 100,000 )
        int N = Integer.parseInt(br.readLine());

        // 화산탄이 N번 날라온다.
        for(int fire = 0; fire < N; fire++) {
            // 날라올 열 위치를 입력받습니다.
            int column = Integer.parseInt(br.readLine());

            while(!dp[column].isEmpty() && map[dp[column].peek().y][dp[column].peek().x] == 'O') {
                dp[column].pop();
            }

            if(dp[column].isEmpty()) {
                run(1, column, column);
            } else {
                run(dp[column].peek().y, dp[column].peek().x, column);
            }
        }
        StringBuilder sb = new StringBuilder();
        // 출력
        for(int i = 1; i <= R; i++){
            for(int j = 1; j <= C; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
    public static void run(int y, int x, int col) {
        while(y + 1 <= R && map[y + 1][x] != 'X') {
            if(map[y + 1][x] == '.') {
                y++;
            } else {
                // 'O'일 경우
                if(!isOver(y, x - 1) && map[y][x - 1] == '.' && map[y + 1][x - 1] == '.') {
                    y++;
                    x--;
                } else if(!isOver(y, x + 1) && map[y][x + 1] == '.' && map[y + 1][x + 1] == '.') {
                    y++;
                    x++;
                } else {
                    break;
                }
            }

            dp[col].push(new Point(x, y));
        }
        map[y][x] = 'O';
    }
    public static boolean isOver(int y, int x) {
        if(y < 0 || y > R || x < 0 || x > C){
            return true;
        }
        return false;
    }

}
