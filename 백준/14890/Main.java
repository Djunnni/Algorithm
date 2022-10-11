import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_14890_경사로
 * author djunnni
 * 
 * 타입 : 구현
 */
public class Main {
    static int N, X, map[][], map2[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");

        // 지도의 한 변길이 N (20)
        N = Integer.parseInt(st.nextToken());
        // 경사로의 길이 X (4), 높이는 항상 1
        X = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        map2 = new int[N][N];

        for (int i = 0; i < N; i++) {
            String data = br.readLine();
            for (int j = 0, index = 0; j < N; j++, index += 2) {
                map2[j][i] = map[i][j] = data.charAt(index) - '0';
            }
        }

        // matrix 출력해보기
        // for(int row[] : matrix) {
        // System.out.println(Arrays.toString(row));
        // }
        System.out.println(process());
    }

    private static int process() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (check(map[i]))
                count++;
            if (check(map2[i]))
                count++;
        }
        return count;
    }

    private static boolean check(int[] road) {
        // 현재 높이
        int currentHeight = road[0];
        // 비교 문자 위치
        int j = 0;
        // 반복문자 위치
        int size = 0;

        while (j < N) {
            // 현재 높이가 도로 j번의 높이와 같다면 1 앞으로 가고 반복문자 사이즈를 1 추가해주자.
            if (currentHeight == road[j]) {
                j++;
                size++;
            } else if (currentHeight - 1 == road[j]) { // 현재 위치가 도로 j번 높이보다 높다면 우측으로 경사로 설치
                int count = 0;
                for (int i = j; i < N; i++) {
                    if (currentHeight - 1 != road[i]) {
                        return false;
                    }
                    if (++count == X)
                        break;
                }
                if (count < X)
                    return false;
                if (count == X) {
                    currentHeight--;
                    j = count + j;
                    size = 0;
                }
            } else if (currentHeight + 1 == road[j]) { // 현재 위치가 도로 j번 높이보다 낮다면 좌측으로 경사로 설치
                if (size < X)
                    return false;
                currentHeight = road[j];
                size = 1;
                j++;
            } else { // 차이가 1 이상일 경우
                return false;
            }
        }

        return true;
    }
}
