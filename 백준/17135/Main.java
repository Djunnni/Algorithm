import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BJ_17135_캐슬디펜스
 *
 * author 이동준
 */
public class Main {
    static List<int[]> archers;
    static int N, M, D, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        D = Integer.parseInt(tokens.nextToken());

        char[][] map = new char[N + 1][M];
        List<int[]> enemies = new ArrayList<>();
        archers = new ArrayList<>();

        for(int r = 0; r < N; r++) {
            String input = br.readLine();
            for(int c = 0, index = 0; c < M; c++, index += 2) {
                map[r][c] = input.charAt(index);
                if(map[r][c] == '1') {
                    enemies.add(new int[]{ r, c });
                }
            }
        }
        // 성은 'C'라고 두겠다.
        Arrays.fill(map[N], 'C');

        // 지도 출력
//        for(char[] row : map) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println("====================");
        // 적들 위치 리스트 출력
//        for(int[] e : enemies) {
//            System.out.println(Arrays.toString(e));
//        }
//        System.out.println("====================");

        // 궁수가 위치할 곳 조합으로 뽑아두기
        int temp[] = new int[3];
        combination(temp, 0, 0);
        temp = null;

        // 궁수가 위치할 위치들 확인해두기
//        for(int[] a : archers) {
//            System.out.println(Arrays.toString(a));
//        }
//        System.out.println("====================");

        for(int[] positions: archers) {
            char[][] _map = copyOfMap(map);
            List<int[]> _enemies = copyOfEnemies(enemies);
            int kill = 0;
            while(!_enemies.isEmpty()) {
                kill += attack(_map, positions, _enemies);
                move(_map, _enemies);
            }
            answer = Math.max(answer, kill);
        }

        System.out.println(answer);
    }
    public static int attack(char[][] map, int[] archers, List<int[]> enemies) {
        int kill = 0;
        Set<int[]> set = new HashSet<>();
        // 궁수들의 열 : position
        for(int position : archers) {
            int minDistance = D;
            int[] minE = null;
            for(int i = 0; i < enemies.size(); i++) {
                int[] enemy = enemies.get(i);
                int distance = getDistance(N, position, enemy[0], enemy[1]);
                if(minDistance > distance) {
                    minDistance = distance;
                    minE = enemy;
                } else if(minDistance == distance) {
                    if(minE == null) {
                        minE = enemy;
                    } else {
                        minE = minE[1] < enemy[1] ? minE : enemy;
                    }
                }
            }
            if(minE != null)
                set.add(minE);
        }
        for(int[] e : set) {
            if(enemies.remove(e)) {
                map[e[0]][e[1]] = '0';
                kill++;
            }
        }


        return kill;
    }
    public static void move(char[][] map, List<int[]> enemies) {
        for(int i = 0; i < enemies.size(); i++) {
            int[] e = enemies.get(i);
            e[0] = e[0] + 1;
            map[e[0] - 1][e[1]] = '0';
            if(e[0] > N || map[e[0]][e[1]] == 'C') {
                enemies.remove(i--);
            } else {
                map[e[0]][e[1]] = '1';
            }
        }
    }
    public static List<int[]> copyOfEnemies(List<int[]> enemies) {
        List<int[]> temp = new ArrayList<>();
        for(int[] e : enemies) {
            temp.add(new int[] { e[0], e[1] });
        }
        return temp;
    }
    public static char[][] copyOfMap(char[][] map) {
        char temp[][] = new char[map.length][map[0].length];

        for(int i = 0; i < map.length; i++) {
            temp[i] = Arrays.copyOf(map[i], map[0].length);
        }
        return temp;
    }
    public static void combination(int[] arr, int cnt, int start) {
        if(cnt == arr.length) {
            archers.add(Arrays.copyOf(arr, arr.length));
            return;
        }

        for(int i = start; i < M; i++) {
            arr[cnt] = i;
            combination(arr, cnt + 1, i + 1);
        }
    }
    public static int getDistance(int x, int y, int x2, int y2) {
        return Math.abs(x2 -x) + Math.abs(y2 - y);
    }
}
