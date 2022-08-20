import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * BJ_2578_빙고
 * 
 * 접근방식
 * Map을 이용해 번호를 부르면 바로 위치를 찾을 수 있게 조치
 * 가로, 세로, 대각선의 빙고를 확인하기 위해 카운트가 차면 line수를 증가
 * 카운트는 확인완료로 0으로 변경작업을 진행
 * 
 * author djunnni
 */
public class Main {
    static int[][] matrix;
    static int answer, line;
    static HashMap<Integer, int[]> map;
    static int[] width, height, slice;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        matrix = new int[5][5];
        map = new HashMap<>();

        width = new int[5]; // 가로 X 개수
        height = new int[5]; // 세로 X 개수
        slice = new int[2]; // 대각선 X 개수 // 0 -> 우하, 1 -> 우상

        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 5; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                // map에 값으로 x, y위치를 알고 있습니다.
                map.put(matrix[i][j], new int[] { i, j });
            }
        }

        for(int i = 1; i <= 5; i ++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= 5; j++) {
                int number = Integer.parseInt(st.nextToken());
                int[] point = map.get(number);
                // 부른 번호에 맞게 해당 위치를 X 표시 한다.
                matrix[point[0]][point[1]] = -1;

                // 사회자가 부른 번째에 X표시에 맞는지 확인하기
                findBingo(point[0], point[1]);

//                for(int[] r : matrix) {
//                    System.out.println(Arrays.toString(r));
//                }
//                System.out.println("===================");
                if(line >= 3) {
                    System.out.println(j + (i - 1) * 5);
                    return;
                }


            }
        }
    }
    public static void findBingo(int x, int y) {
        width[x]++;
        height[y]++;
        if(x == y) {
            slice[0]++;
        }
        if(4 - y == x) {
            slice[1]++;
        }

        if(width[x] == 5) {
            line++;
            width[x] = 0;
        }
        if(height[y] == 5) {
            line++;
            height[y] = 0;
        }
        if(slice[0] == 5) {
            line++;
            slice[0] = 0;
        }
        if(slice[1] == 5) {
            line++;
            slice[1] = 0;
        }
    }
}
