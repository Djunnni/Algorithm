import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BJ_1992_쿼드트리
 * 2초, 128MB
 * 
 * 풀이방법
 * 
 * 4사분면으로 나누되 SIZE가 1일 경우엔 바로 그 위치값 리턴
 * 0000이면 0으로 1111이면 1로 그도 아니면 sub case로 () 넣기
 * author djunnni
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[][] matrix = new char[N][N];
        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < N; j++) {
                matrix[i][j] = input.charAt(j);
            }
        }

        String answer = quadTree(matrix, N, 0, 0);

        System.out.println(answer);
    }
    public static String quadTree(char[][] matrix, int size, int x, int y) {
        if(size == 1) {
            return matrix[y][x] + "";
        }
        String data = "";

        data += quadTree(matrix, size / 2, x, y);
        data += quadTree(matrix, size / 2, x + size / 2, y);
        data += quadTree(matrix, size / 2, x, y + size / 2);
        data += quadTree(matrix, size / 2, x + size / 2, y + size / 2);

        if(data.equals("0000")) {
            return "0";
        } else if(data.equals("1111")) {
            return "1";
        } else {
            return "(" + data + ")";
        }
    }
}
