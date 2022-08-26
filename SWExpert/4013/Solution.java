import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * author djunnni
 */
public class Solution {
    static char blade[][];
    static int spinCount[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        // 테스트케이스 수
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");

            // 자석을 회전시키는 횟수 K
            int K = Integer.parseInt(br.readLine());

            // 자석에 붙어있는 날의 정보를 저장.
            blade = new char[5][8];

            // 자석의 헤드가 바라보는 위치 이동 값.
            spinCount = new int[5];

            for(int i = 1; i <= 4; i++) {
                String input = br.readLine();
                for(int j = 0, index = 0; j < 8; j++, index += 2) {
                    // 날의 자성은 N극이 0, S극이 1
                    // 자성정보는 빨간색 화살표 위치의 날부터 시계방향으로 주어진다.
                    blade[i][j] = input.charAt(index) == '1' ? 'S' : 'N';
                }
            }
            // 날의 정보 확인
//            for(char[] row : blade) {
//                System.out.println(Arrays.toString(row));
//            }
//            System.out.println("======================");
//            System.out.println(Arrays.toString(spinCount));
            // K번만큼 i번 칼날을 회전시킵니다.
            for(int k = 0; k < K; k++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int bladeNo = Integer.parseInt(st.nextToken());
                // spin이 1이면 시계방향, 1이 아니면 반시계다.
                boolean spin = Integer.parseInt(st.nextToken()) == 1;
                boolean visited[] = new boolean[5];
                repeat(visited, bladeNo, spin);

            }
            // System.out.println(Arrays.toString(spinCount));
            int answer = 0;

            // 날 정보 확인하기
            for(int i = 1; i <= 4; i++) {
                int count = getCount(i);
                if(blade[i][count % 8] == 'S') {
                    answer += Math.pow(2, (i - 1));
                }
            }
            sb.append(answer).append("\n");
       }
        System.out.print(sb);
    }
    public static void repeat(boolean[] visited, int bladeNo,boolean spin) {
        visited[bladeNo] = true;
        boolean doLeft = checkBalanceLeft(bladeNo);
        boolean doRight = checkBalanceRight(bladeNo);
        spin(bladeNo, spin);

        if(doLeft && !visited[bladeNo - 1]) {
            repeat(visited, bladeNo - 1, !spin);
        }
        if(doRight && !visited[bladeNo + 1]) {
            repeat(visited, bladeNo + 1, !spin);
        }
    }
    public static boolean checkBalanceLeft(int bladeNo) {
        if(bladeNo == 1) return false;

        return checkBalance(bladeNo - 1, bladeNo);
    }
    public static boolean checkBalanceRight(int bladeNo) {
        if(bladeNo == 4) return false;

        return checkBalance(bladeNo, bladeNo + 1);
    }
    /**
     * blade 1,2번 붙은 곳이 같은 문자라면 balance가 아니다라는 것. 다른 문자라면 balance 하다.
     */
    public static boolean checkBalance(int bladeNo1, int bladeNo2) {
        char bladeStatus1 = ' ';
        char bladeStatus2 = ' ';
        // 날번호가 2번이 더 크다면 2번은 왼쪽을, 1번은 오른쪽을 비교하는 상황이다.
        if(bladeNo1 < bladeNo2) {
            bladeStatus1 = blade[bladeNo1][(getCount(bladeNo1) + 2) % 8];
            bladeStatus2 = blade[bladeNo2][(getCount(bladeNo2) + 6) % 8];
        } else {
            // 날번호가 1번이 더 크다면 1번은 오른쪽을, 2번은 왼쪽을 비교하는 상황이다.
            bladeStatus1 = blade[bladeNo1][(getCount(bladeNo1) + 6) % 8];
            bladeStatus2 = blade[bladeNo2][(getCount(bladeNo2) + 2) % 8];
        }

        if(bladeStatus1 == bladeStatus2) {
            return false;
        }
        return true;
    }
    /**
     * 블레이드 번호에 맞게 시계방향으로 회전시킨다.
     * 추가적인 발생 상황:
     * 만약 다음 bladeNo의 칼날과 붙은 점의 문자가 같다면!
     *          두번째 톱니바퀴를 회전시킨다. 위의 루프 계속 ! (3 -> 4 -> 추가적인 회전이 필요함.)
     * 문자가 다르다면!
     *          추가적인 회전은 없다.
     */
    public static void spin(int bladeNo, boolean clockwise) {
        // 시계방향으로 돌리면 spinCount는 -1한다. 반시계면 spinCount는 1 더한다.
        if(clockwise)
            spinCount[bladeNo]--;
        else
            spinCount[bladeNo]++;
    }
    public static int getCount(int id) {
        int count = spinCount[id];
        if(count < 0) {
            count += ((Math.abs(count) / 8) + 1) * 8;
        }
        return count;
    }
}
